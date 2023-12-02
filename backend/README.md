###### <h1 style="text-align: center;"> <img src="https://em-content.zobj.net/source/apple/354/card-file-box_1f5c3-fe0f.png" width="35"/> Backend API <img src="https://em-content.zobj.net/source/apple/354/card-file-box_1f5c3-fe0f.png" width="35"/> </h1>

## Technologies Used:
<ul>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/java.png"/> <b>Java</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/spring.png"/> <b>Spring Boot</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/spring.png"/> <b>Spring Security</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/spring.png"/> <b>Spring Data JPA</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/postgresql.png"/> <b>PostgreSQL</b>
</li>
<li>
<img style="width: 22px; height: 22px; object-fit: cover;" src="assets/telegram.png" /> <b>Telegram API</b>
</li>
<li>
<img style="width: 22px; height: 22px; object-fit: cover;" src="assets/chatgpt.png" /> <b>ChatGPT API</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/swagger.png"/> <b>Swagger</b>
</li>
<li>
<img style="width: 25px; height: 25px; " src="assets/docker.png"/> <b>Docker</b>
</li>
<li>
<img style="width: 25px; height: 25px;" src="assets/lombok.png"/> <b>Lombok</b>
</li>
<li>
<img style="width: 25px; height: 25px; " src="assets/mapstruct.png"/> <b>MapStruct</b>
</li>
</ul>
<h5>Description:</h5>
<ol>
<li>Uses Spring Data JPA for simplified data access and persistence.</li>
<li>The PostgreSQL database is employed to store chat histories securely.</li>
<li>Integrates Swagger for API documentation, providing a clear and accessible reference for developers.</li></ul>
<li>The frontend is developed with JavaScript, TypeScript, HTML, and Sass.</li>
</ol>

## API Endpoints <img src="https://em-content.zobj.net/thumbs/160/apple/354/link_1f517.png" width="25"/>
### 1. Admin Login
- **Endpoint**: `/admin/login`
- **Method**: `POST`
- **Description**: Authenticate an admin and generate a JWT token for authorization.
### 2. Admin Registration
- **Endpoint**: `/admin/register`
- **Method**: `POST`
- **Description**: Register a new admin, returning the created admin along with a generated JWT token.
### 3. Get All Users
- **Endpoint**: `/admin/user`
- **Method**: `GET`
- **Description**: Retrieve a list of users with optional pagination and sorting parameters.
### 4. Get User Chat History
- **Endpoint**: `/admin/user/chat-history`
- **Method**: `GET`
- **Description**: Retrieve the chat history of a specific user with optional pagination and sorting parameters.
### 5. Get User Chat History
- **Endpoint**: `/admin/chatGPT`
- **Method**: `GET`
- **Description**: Initiate a chat with the GPT model by providing a prompt.
