<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
        <style>
            table,
            tr,
            td,
            th {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 5px 10px;
                text-align: center;
            }

            th {
                background-color: beige;
            }

            tr:nth-child(even) {
                background-color: azure;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->
            <div v-if="!authFlg">
                <div>
                    <label>아이디 : <input v-model="userId"></label>
                </div>

                <div>
                    <label>이름 : <input v-model="name"></label>
                </div>

                <div>
                    <label>번호: <input placeholder="-를 제외하고 입력해주세요" v-model="phone"></label>
                </div>

                <div>
                    <button @click="fnAuth">인증</button>
                </div>

            </div>
            <div v-else>
                <div>
                    <label>비밀번호 : <input v-model="pwd1"></label>
                </div>

                <div>
                    <label>비밀번호 확인 : <input v-model="pwd2"></label>
                </div>

                <div>
                    <button @click="fnChange">변경</button>
                </div>
            </div>
        </div>

    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    authFlg: false,
                    userId: "",
                    name: "",
                    phone: "",
                    pwd1: "",
                    pwd2: "",

                };
            },
            methods: {
                // 함수(메소드) - (key : function())  
                fnAuth: function () {
                    let self = this;
                    // console.log("공백 제거 전 ==>", self.userId);
                    // console.log("공백 제거 전 ==>", self.userId.trim());
                    let param = {
                        userId: self.userId,
                        name: self.name,
                        phone: self.phone
                    };
                    $.ajax({
                        url: "/member/find.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            if (data.result == "success") {
                                alert("인증되셨습니다.");
                                self.authFlg = true;
                            } else {
                                alert("회원 정보를 확인해주세요.");

                            }
                        }

                    });
                },

                fnChange: function () {
                    let self = this;
                    let param = {
                        userId: self.userId,
                        id : self.userId,
                        pwd: self.pwd1
                    };
                    $.ajax({
                        url: "/member/pwd.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            if (data.result == "success") {
                                alert("변경되셨습니다.");      
                                // location.href="/member/login.do";              
                            } else {
                                alert("오류가 발생하셨습니다");

                            }
                        }
                    });
                }
            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;

            }
        });

        app.mount('#app');
    </script>