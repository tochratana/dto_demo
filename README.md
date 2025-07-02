================================ 
8. USAGE EXAMPLES & TEST DATA

================================


SAMPLE DATA (Pre-loaded in UserRepository):
1. ID: 1, Name: John Doe, Email: john.doe@example.com, Phone: +1234567890
2. ID: 2, Name: Jane Smith, Email: jane.smith@example.com, Phone: +0987654321
3. ID: 3, Name: Bob Johnson, Email: bob.johnson@example.com, Phone: +1122334455

API ENDPOINTS:

1. GET /api/users - Get all users
2. GET /api/users/1 - Get user by ID
3. GET /api/users/email/john.doe@example.com - Get user by email
4. GET /api/users/search?name=John - Search users by name
5. GET /api/users/stats - Get user statistics
6. POST /api/users - Create new user
7. PUT /api/users/1 - Update user
8. DELETE /api/users/1 - Delete user

EXAMPLE JSON REQUESTS:

Create User (POST /api/users):
{
"firstName": "Alice",
"lastName": "Wilson",
"email": "alice.wilson@example.com",
"password": "securePassword123",
"phoneNumber": "+1555666777"
}

Update User (PUT /api/users/1):
{
"firstName": "Johnny",
"phoneNumber": "+1999888777"
}

EXAMPLE RESPONSES:

Success Response:
{
"id": 4,
"firstName": "Alice",
"lastName": "Wilson",
"email": "alice.wilson@example.com",
"phoneNumber": "+1555666777",
"createdAt": "2024-01-15T10:30:00",
"updatedAt": "2024-01-15T10:30:00"
}

Error Response:
{
"status": 400,
"error": "Validation Failed",
"message": "Invalid input data",
"timestamp": "2024-01-15T10:30:00",
"validationErrors": [
"First name is required",
"Please provide a valid email address"
]
}
