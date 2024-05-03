# Embedded Peaka Java Example

Sample java project to demonstrate Embedded Peaka UI integration and [Peaka JDBC Driver](https://github.com/peakacom/peaka-jdbc-driver) usage.
Project has backend and frontend implementations. Backend is implemented using Spring Boot framework. Backend
makes API calls to Peaka Partner API. You can check the Partner API details from Peaka [Documentation.](https://docs.peaka.com/api-reference/introduction)
Frontend is implemented with vite, react, tailwind and radix-ui.

You need to run backend and frontend together. Here is how you can do the setup for both of them:

## Backend Setup

Open a terminal go into backend folder.

```bash
cd backend
```

Open the application properties file with an editor, we are using nano in the example belowe:

```bash
nano src/main/resources/application.properties
```

Replace `<YOUR_PARTNER_API_KEY>` with your own partner API Key. Then install dependencies with maven by running:

```bash
./mvnw clean install
```

Then start spring boot app with following command.

```bash
./mvnw spring-boot:run
```

Your backend app should start running on port `8080`.

## Frontend Setup

Open a terminal go into frontend folder.

```bash
cd frontend
```

Install dependencies using npm then start project with commands below.

```bash
npm install
npm run dev
```

Your frontend app should start running on port `5173`

## Contact

For feature requests and bugs, please create an issue in this repo. For further support, see the following resources:

- [Peaka Community Discord](https://discord.com/invite/peaka)
