<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SB Admin 2 - Login (Ajax)</title>

    <!-- CSS -->
    <link href="${contextPath}/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="${contextPath}/static/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${contextPath}/static/font/nunito/nunito.css" rel="stylesheet">

    <!-- JS -->
    <script src="${contextPath}/static/vendor/jquery/jquery.min.js"></script>
    <script src="${contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/static/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="${contextPath}/static/js/sb-admin-2.min.js"></script>
</head>
<body class="bg-gradient-primary">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>

                                <!-- Ajax Login Form -->
                                <form id="loginForm" class="user">
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user"
                                               name="email" id="email" placeholder="Enter Email Address..." required>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user"
                                               name="password" id="password" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input type="checkbox" class="custom-control-input" id="rememberMe"
                                                   name="rememberMe">
                                            <label class="custom-control-label" for="rememberMe">Remember Me</label>
                                        </div>
                                    </div>
                                    <button type="button" id="loginBtn" class="btn btn-primary btn-user btn-block">
                                        Login
                                    </button>
                                    <hr>
                                    <a href="" class="btn btn-google btn-user btn-block">
                                        <i class="fab fa-google fa-fw"></i> Login with Google
                                    </a>
                                    <a href="" class="btn btn-facebook btn-user btn-block">
                                        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                    </a>
                                </form>

                                <hr>
                                <div class="text-center">
                                    <a class="small" href="${contextPath}/auth/forgot-password">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="${contextPath}/auth/register">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#loginBtn').click(function() {
            const data = {
                email: $('#email').val(),
                password: $('#password').val(),
                rememberMe: $('#rememberMe').is(':checked')
            };

            $.ajax({
                url: '${contextPath}/auth/login', // Ajax 로그인 엔드포인트
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    if (response.success) {
                        window.location.href = '${contextPath}/home';
                    } else {
                        alert(response.message || 'Login failed');
                    }
                },
                error: function(xhr) {
                    alert('Server error: ' + xhr.status);
                }
            });
        });
    });
</script>

</body>
</html>
