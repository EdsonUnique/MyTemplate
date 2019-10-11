<html>
    <head>
        <title>订单详情页面</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <#include "./common/header.ftl">
                <table class="table table-hover table-bordered" style="text-align: center">
                    <thead>
                    <tr >
                        <th>
                            订单编号
                        </th>
                        <th>
                            总金额
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="info">
                        <td>
                            ${orderVO.orderId}
                        </td>
                        <td>
                            ${orderVO.orderAmount}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <br><br>
                <table class="table table-hover table-bordered" style="text-align: center">
                    <thead>
                    <tr>
                        <th>
                            订单项编号
                        </th>
                        <th>
                            商品编号
                        </th>
                        <th>
                            商品名称
                        </th>
                        <th>
                            商品图片
                        </th>
                        <th>
                            购买数量
                        </th>
                        <th>
                            单价
                        </th>
                        <th>
                            小计
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderVO.orderItemList as orderItem>
                    <tr class="info">
                        <td>
                            ${orderItem.detailId}
                        </td>
                        <td>
                            ${orderItem.productId}
                        </td>
                        <td>
                            ${orderItem.productName}
                        </td>
                        <td>
                            ${orderItem.productIcon}
                        </td>
                        <td>
                            ${orderItem.productQuantity}
                        </td>
                        <td>
                            ${orderItem.productPrice}
                        </td>
                        <td>
                            ${orderItem.productPrice*orderItem.productQuantity}
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>

                <#if orderVO.orderStatus==0>
			        <button class="btn btn-primary" type="button" onclick="javascript:window.location.href='/sell/sellOrder/finishOrder/${orderVO.orderId}'">完结订单</button>&nbsp;&nbsp;
                    <button class="btn btn-primary" type="button" onclick="javascript:window.location.href='/sell/sellOrder/cancelOrder/${orderVO.orderId}'">取消订单</button>
                    <button class="btn btn-primary" type="button" onclick="history.go(-1);">返回</button>
                <#else >
                        <button class="btn btn-primary" type="button" onclick="history.go(-1);">返回</button>
                </#if>
            </div>
        </div>
    </div>
    </body>

</html>