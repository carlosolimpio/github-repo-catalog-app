package com.demo.githublogin.presentation

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.demo.githublogin.databinding.FragmentGithubLoginBinding
import com.demo.githublogin.domain.model.Token
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ClientSecretBasic
import org.koin.android.ext.android.inject

class GithubLoginFragment : Fragment() {
    private lateinit var binding: FragmentGithubLoginBinding

    private val service: AuthorizationService by inject()
    private val request: AuthorizationRequest by inject()
    private val secret: ClientSecretBasic by inject()
    private val viewModel: SaveUserLoginTokenViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGithubLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            performGithubAuth()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.dispose()
    }

    private val launcher = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let { data ->
                val result = AuthorizationResponse.fromIntent(data)
                val tokenRequest = result?.createTokenExchangeRequest()

                service.performTokenRequest(tokenRequest!!, secret) { response, exception ->
                    response?.let { token ->
                        viewModel.saveUserLoginToken(
                            Token(
                                token.accessToken ?: "",
                                token.tokenType ?: "",
                            ),
                        )
                    } ?: throw exception!!
                }
            } ?: throw AuthorizationException.fromIntent(it.data)!!
        } else {
            throw Exception("Result code not ok: ${it.resultCode}")
        }
    }

    private fun performGithubAuth() {
        val intent = service.getAuthorizationRequestIntent(request)
        launcher.launch(intent)
    }
}
