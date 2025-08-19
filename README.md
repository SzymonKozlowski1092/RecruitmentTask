# Recruitment Task – GitHub Repositories API

## Project Description
This application provides a **REST API** that allows retrieving all **non-fork repositories** of a given GitHub user.  

If something goes wrong, the API returns a error in JSON format:
```json
{
  "status": ${responseCode}
  "message": ${whyHasItHappened}
}
```

## Running the Application

#### 1. Clone the repository

```bash
git clone https://github.com/https://github.com/SzymonKozlowski1092/RecruitmentTask.git
cd RecruitmentTask
```
#### 2. Configuration
Add and set your GitHub API configuration in application-local.properties:

```bash
github.base.url=https://api.github.com
github.token=ghp_xxxxxxxxxxxxxxxxxxxxx
```

#### 3. Build and run
```bash
./mvnw spring-boot:run
```
## API Reference

```http
  GET /api/repositories/{username}
```
Returns all non-fork repositories of a given user.

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. GitHub username of the user to fetch repositories for |

Response: 
```bash
[
  {
    "name": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
      }
    ]
  }
]
```

## Tests

The project includes tests under src/test/java:

- **single integration test** focused on the business **happy path** for: 
`GET /repositories/{username}`

Run tests with:

```bash
  ./mvnw test
```


## Authors

- [@SzymonKozlowski1092](https://www.github.com/SzymonKozlowski1092)

