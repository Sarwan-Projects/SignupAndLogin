# User Authentication Web Application

This is a web application for user authentication, including **sign up**, **login**, and **forgot password** features. The application uses JDBC for database interaction and includes OTP-based verification for password recovery. 

## Features

- **Sign Up**:
  - Users can create a new account by providing their name, email, password, re-enter password, mobile number, and agreeing to the terms and conditions.
  - Password and email are validated with regular expressions (patterns).
  - Users can only submit the form when all fields are filled and the terms & conditions checkbox is checked.

- **Login**:
  - Users can log in using their email and password.

- **Forgot Password**:
  - Users can reset their password by receiving an OTP to their registered email address for verification and security.

- **Responsive Design**:
  - The application uses **Bootstrap** to ensure a responsive and user-friendly interface across devices.

## Technologies Used

- **Java** (Servlets)
- **JSP** (for frontend pages)
- **JDBC** (for database interaction)
- **Bootstrap** (for responsive design and UI components)
- **MySQL** (or another relational database of your choice)

## File Structure

- `Login.jsp`: The login page where users enter their email/username and password.
- `registration.jsp`: The sign-up page where users can create a new account.
- `forgotPassword.jsp`: The page where users can request a password reset via OTP.
- `EnterOtp.jsp`: The page for users to verify their OTP and reset their password.
- `index.jsp`: The homepage for authenticated users after login.
- `newPassword.jsp`: A page that shows errors during login, sign up, or password reset.

- `src/`: Contains all Java Servlet files.
  - `ValidateOtp.java`: Handles user login functionality.
  - `RegistrationServlet.java`: Handles user registration and sign-up validation.
  - `ForgotPassword.java`: Manages OTP generation and email sending for password recovery.
  - `Logout.java`: Handles OTP verification for resetting the password.
  - `Login.java`: Allows users to reset their password after OTP verification.
  - `NewPassword.java`: Displays the user's dashboard after successful login.

- `WEB-INF/`: Contains configuration files like `web.xml` for general settings (like welcome file).

## Setup Instructions

### Prerequisites

- JDK (Java Development Kit) 8 or higher
- MySQL or any relational database of your choice
- Apache Tomcat 10.1 or any Servlet container
- IDE (like Eclipse, IntelliJ, etc.) with Java support

### Steps to Run the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Sarwan-Projects/SignupAndLogin.git
