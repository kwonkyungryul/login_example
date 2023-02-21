<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="../layout/header.jsp" %>
        <div class="container my-3">
            <div class="container">
                <h2>회원가입</h2>
                <form action="/join" method="post">
                    <div class="d-flex form-group mb-2">
                        <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
                    </div>
    
                    <div class="form-group mb-2">
                        <input type="password" name="password" class="form-control" placeholder="Enter password"
                            id="password">
                    </div>
    
                    <div class="form-group mb-2">
                        <input type="email" name="email" class="form-control" placeholder="Enter email" id="email">
                    </div>
    
                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>
    
            </div>
        </div>
    <%@ include file="../layout/footer.jsp" %>