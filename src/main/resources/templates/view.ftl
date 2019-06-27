<html>
    <head>
        <title>订单列表</title>
        <#include "common/bootstrapCss.ftl">
    </head>
    <body>

<div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <#include "./common/header.ftl">
                <table class="table table-hover table-bordered" style="text-align: center">
                    <thead>
                    <tr>
                        <th>
                            订单号
                        </th>
                        <th>
                            买家微信号
                        </th>
                        <th>
                            买家电话
                        </th>
                        <th>
                            订单总金额
                        </th>
                        <th>
                            订单状态
                        </th>
                        <th>
                            支付状态
                        </th>
                        <th>
                            下单时间
                        </th>
                        <th colspan="2">
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderPage.content as orderOne>
                        <tr>
                            <td>
                                ${orderOne.orderId}
                            </td>
                            <td>
                                ${orderOne.buyerOpenId}
                            </td>
                            <td>
                                ${orderOne.buyerPhone}
                            </td>
                            <td>
                                ${orderOne.orderAmount}
                            </td>
                            <td>
<#--                                <#if orderOne.orderStatus==0>新订单-->
<#--                                <#elseIf orderOne.orderStatus==1>订单已取消-->
<#--                                <#elseIf orderOne.orderStatus==2>订单已完结-->
<#--                                </#if>-->
                                ${orderOne.getOrderStatusEnumMsg()}
                            </td>
                            <td>
<#--                                <#if orderOne.payStatus==0>-->
<#--                                    等待支付-->
<#--                                <#else>已支付-->
<#--                                </#if>-->
                                ${orderOne.getPayStatusEnumMsg()}
                            </td>
                            <td>
                                ${orderOne.createTime}
                            </td>
                            <td>
                                <a class="arrows-on-left-vertical" href="/sell/sellOrder/viewOrderItems/${orderOne.orderId}">订单详情</a>

                            </td>
                            <td>
                                <a class="arrows-on-left-vertical" href="/sell/sellOrder/cancelOrder/${orderOne.orderId}">取消订单</a>

<#--                                <#if orderOne.orderStatus==0>-->
<#--                                    <a class="arrows-on-left-vertical" href="/sell/sellOrder/cancelOrder/${orderOne.orderId}">取消订单</a>-->
<#--                                    <#else >-->
<#--                                    <span class="disabled"><a class="arrows-on-left-vertical" href="#">取消订单</a></span>-->
<#--                                </#if>-->
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-12" >
                <nav>
                    <ul class="pagination float-right">

                        <#if currentPage==1>
                            <li class="page-item disabled">
                                <a class="page-link "  href="#">上一页</a>
                            </li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link " href="/sell/sellOrder/view?pagenum=${currentPage-1}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..orderPage.getTotalPages() as index>
                            <li class="page-item">
                                <a class="page-link" href="/sell/sellOrder/view?pagenum=${index}">${index}</a>
                            </li>
                        </#list>

                        <#if currentPage==orderPage.getTotalPages()>
                            <li class="page-item disabled">
                                <a class="page-link " href="#">下一页</a>
                            </li>
                            <#else>
                                <li class="page-item">
                                    <a class="page-link" href="/sell/sellOrder/view?pagenum=${currentPage+1}">下一页</a>
                                </li>
                        </#if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

<#--    新订单提示信息-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">新消息：</h4>

                    <button type="button" onclick="location.reload();" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div id="messageModal" class="modal-body">
                    您有新订单
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="location.reload();" class="btn btn-primary">
                        关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    </body>

<#--    消息铃声播放-->
    <audio id="message_mp3" loop="loop">
        <source src="/sell/mp3/message.mp3" type="audio/mpeg">
    </audio>
<#--    websocket-->

    <script>

        if('WebSocket' in window){

            var websocket=new WebSocket("ws://localhost:8080/sell/websocket");

            websocket.onopen=function (ev) {
                console.log("建立websocket链接");
            }

            websocket.onclose=function (ev) {
                console.log("连接取消");
            }

            websocket.onmessage=function (ev) {
                console.log("获取信息：" + ev.data);
                $('#messageModal').html(ev.data);
                $('#myModal').modal('show');
                document.getElementById('message_mp3').play();
                console.log("发送消息");
            }

            websocket.onbeforeunload=function (ev) {
                console.log("链接建立之前先关闭之前的链接");
            }

        }else{
            alert("您的浏览器不支持WebSocket");
        }


    </script>

</html>

