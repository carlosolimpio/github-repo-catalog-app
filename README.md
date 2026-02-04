# Github Repository Catalog App

## Storing the secrets
Store the necessary secrets in `local.properties` file.

## Note on Apollo GraphQL:
  * in order to download schema, first I needed to set in gradle.properties: ```android.defaults.buildfeatures.buildconfig=true```
  * then download the schema manually using:
    ```bash
    ./gradlew downloadApolloSchema \
    --endpoint="https://api.github.com/graphql" \
    --schema="features/repolist/src/main/graphql/schema.graphqls" \
    --header="Authorization: Bearer <API TOKEN HERE>"```
  * then do a `gradle sync` to make the introspection block work
