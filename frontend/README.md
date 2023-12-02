###### <h1 style="text-align: center;"> <img src="https://em-content.zobj.net/source/apple/354/laptop_1f4bb.png" width="35"/> Frontend UI <img src="https://em-content.zobj.net/source/apple/354/laptop_1f4bb.png" width="35"/> </h1>

## Technologies Used:
<ul>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/javascript.png" width="25"/> <b>JavaScript</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/typescript.png" width="25"/> <b>TypeScript</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/bootstrap.png"/> <b>Bootstrap</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/jwt.png"/> <b>JWT (JSON Web Token)</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/angular.png"/> <b>Angular</b>
</li>
<li>
<img style="width: 25px; height: 25px; object-fit: cover;" src="assets/html.png"/> <b>HTML</b>
</li>
<li>
<img style="width: 22px; height: 22px; object-fit: cover;" src="assets/sass.png"/> <b>Sass</b>
</li>
</ul>
<h5>Description:</h5>
<ol>
<li>The Website UI is implemented as a RESTful application, 
offering flexibility using the Angular and Bootstrap frameworks.</li>
<li>Developed secure login and registration form for admins.</li>
<li>Developed a secure form for management user requests and bot responses.</li>
<li>The frontend is developed with JavaScript, TypeScript, HTML, and Sass.</li>
</ol>

## UI Endpoints <img src="https://em-content.zobj.net/thumbs/160/apple/354/link_1f517.png" width="25"/>
### 1. Main page
- **Endpoint**: `/admin/main`
- **Description**: Display main description application page.
### 2. Login and registration page
- **Endpoint**: `/admin/login`
- **Method**: `POST`
- **Description**: Authenticate an admin and generate a JWT token for authorization.
### 3. Content page
- **Endpoint**: `/admin/content`
- **Method**: `GET`
- **Description**: Display list of users for registered or authenticating admins with optional pagination and sorting parameters.
### 4. Chat histories page
- **Endpoint**: `/admin/user/chat-history`
- **Method**: `GET`
- **Description**: Display list of chat histories of specified users for registered or authenticating admins with optional pagination and sorting parameters.
