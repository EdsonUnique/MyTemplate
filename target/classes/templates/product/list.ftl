<html>
<head>
    <title>商品列表</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <#include "../common/header.ftl">
            <table class="table table-hover table-bordered" style="text-align: center">
                <thead>
                <tr>
                    <th>
                        商品编号
                    </th>
                    <th>
                        商品名称
                    </th>
                    <th>
                        单价
                    </th>
                    <th>
                        库存
                    </th>
                    <th>
                        详细描述
                    </th>
                    <th>
                        商品图片
                    </th>
                    <th>
                        所属类别
                    </th>
                    <th>
                        商品状态
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list modelPage.content as modelOne>
                    <tr>
                        <td>
                            ${modelOne.productId}
                        </td>
                        <td>
                            ${modelOne.productName}
                        </td>
                        <td>
                            ${modelOne.productPrice}
                        </td>
                        <td>
                            ${modelOne.productStock}
                        </td>
                        <td>
                            ${modelOne.productDescription}
                        </td>
                        <td>
                            ${modelOne.productIcon}
                        </td>
                        <td>
                            ${modelOne.categoryType}
                        </td>
                        <td>
                            ${modelOne.GetProductStatusMsg()}
                        </td>
                        <td>
                            <a class="arrows-on-left-vertical" href="/sell/product/addOrUpdateUI?productId=${modelOne.productId}">修改</a>

                        </td>
                        <td>
                            <#if modelOne.GetProductStatusMsg()=="已下架">
                                <a class="arrows-on-left-vertical" href="/sell/product/dropProduct?productId=${modelOne.productId}">上架</a>
                                <#else >
                                    <a class="arrows-on-left-vertical" href="/sell/product/pushProduct?productId=${modelOne.productId}">下架</a>
                            </#if>

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
                            <a class="page-link " href="/sell/product/list?pagenum=${currentPage-1}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..modelPage.getTotalPages() as index>
                        <li class="page-item">
                            <a class="page-link" href="/sell/product/list?pagenum=${index}">${index}</a>
                        </li>
                    </#list>

                    <#if currentPage==modelPage.getTotalPages()>
                        <li class="page-item disabled">
                            <a class="page-link " href="#">下一页</a>
                        </li>
                    <#else>
                        <li class="page-item">
                            <a class="page-link" href="/sell/product/list?pagenum=${currentPage+1}">下一页</a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>

