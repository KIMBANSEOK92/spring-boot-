<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="/js/page-change.js"></script>
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: beige;
        }
        tr:nth-child(even){
            background-color: azure;
        }
        th{
            width: 50px;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <table>
            <tr>
                    <th>제목</th>
                    <td>{{info.title}}</td>
            </tr>

            <tr>
                    <th>조회수</th>
                    <td>{{info.hit}}</td>
            </tr>

            <tr>
                    <th>시간 </th>
                    <td>{{info.cdatetime}}</td>
            </tr>

            <tr>
                    <th>내용 </th>
                    <td>{{info.contents}}</td>
            </tr>

        </table>

        <div>
            <button @click="fnEdit">수정</button>
            <button><a href="list.do" style="text-decoration: none; color: inherit;">되돌아가기</a></button>
        </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                bbsNum : "${bbsNum}",
                info : {},
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnInfo : function () {
                let self = this;
                let param = {
                    bbsNum : self.bbsNum
                };
                $.ajax({
                    url: "/bbs/view.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        if(data.result == "success"){
                            self.info = data.info;
                        } else {
                            alert("오류가 발생했습니다!");
                        }
                    }
                });
            },
            fnEdit : function(){
                let self = this;
                pageChange("/bbs/edit.do", {bbsNum : self.bbsNum});
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnInfo();
        }
    });

    app.mount('#app');
</script>