AuthService :-

This service is responsible for providing authentication and authorization functionalities for the entire system. 

It handles user signup, login, JWT token generation, and refresh token management.

The user credentials are securely stored in the database with passwords encrypted using industry-standard encryption techniques. 


This service serves as the central point for all authentication and authorization operations within the system.

Key Features :-

User Signup & Login: Enables users to register and authenticate with the system.

JWT Token Generation: Issues JWT (JSON Web Tokens) for secure user authentication.

Refresh Tokens: Supports token refreshing to allow seamless session management.

Password Encryption: Stores user passwords securely in the database using bcrypt encryption.
