<html>
<head>
    <title>
        添加商品
    </title>
    <#include "../common/bootstrapCss.ftl">
    <script defer="defer" src="/sell/defer_js/form_message.js">
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <#include "../common/header.ftl">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <form method="post" class="needs-validation" role="form" novalidate action="/sell/product/addOrUpdate">
                            <input type="hidden" name="productId" value="${(product.productId)!''}">
                            <div class="form-row">
                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom01">商品名称</label>
                                    <input type="text" class="form-control" name="productName" id="validationCustom01"  value="${(product.productName)!''}" required>
                                    <div class="invalid-feedback">
                                        商品名称不能为空！
                                    </div>
                                </div>

                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom02">商品单价</label>
                                    <input type="text" class="form-control" name="productPrice" id="validationCustom02"  value="${(product.productPrice)!''}" required>
                                    <div class="invalid-feedback">
                                        商品单价不能为空！
                                    </div>
                                </div>
                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom01">商品库存</label>
                                    <input type="number" class="form-control" name="productStock" id="validationCustom01" value="${(product.productStock)!''}" required>
                                    <div class="invalid-feedback">
                                        商品库存不能为空！
                                    </div>
                                </div>

                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom02">商品描述</label>
                                    <textarea id="validationCustom02" class="form-control" name="productDescription" rows="3" required>${(product.productDescription)!''}</textarea>
                                    <div class="invalid-feedback">
                                        商品描述不能为空！
                                    </div>
                                </div>
                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom01">商品图片</label>
                                    <input type="text" class="form-control" id="validationCustom01" name="productIcon"  value="${(product.productIcon)!''}" required>
                                    <div class="invalid-feedback">
                                        商品图片不能为空！
                                    </div>
                                </div>

                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom02">商品类别</label>
                                    <select id="validationCustom02" name="categoryType" required class="form-control">
                                        <#list productCategoryList as category>
                                            <option value="${category.categoryType}"
                                                 <#if (product.categoryType)?? && product.categoryType==category.categoryType>selected</#if>  >${category.categoryName}</option>
                                        </#list>

                                    </select>
                                    <div class="invalid-feedback">
                                        商品类别不能为空！
                                    </div>
                                </div>
                                <div class="col-md-8 mb-8">
                                    <label for="validationCustom02">商品状态</label>
                                    <select id="validationCustom02" name="productStatus" required class="form-control">

                                        <option value="1" <#if (product.productStatus)??&& product.productStatus==1> selected</#if> >上架</option>
                                        <option value="0" <#if (product.productStatus)?? && product.productStatus==0> selected</#if> >下架</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        商品状态不能为空！
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-primary" type="submit"><#if (product.productId)??>修改
                                <#else >添加
                                </#if></button>
                        </form>

                        <script>
                            // Example starter JavaScript for disabling form submissions if there are invalid fields
                            (function() {
                                'use strict';
                                window.addEventListener('load', function() {
                                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                    var forms = document.getElementsByClassName('needs-validation');
                                    // Loop over them and prevent submission
                                    var validation = Array.prototype.filter.call(forms, function(form) {
                                        form.addEventListener('submit', function(event) {
                                            if (form.checkValidity() === false) {
                                                event.preventDefault();
                                                event.stopPropagation();
                                            }
                                            form.classList.add('was-validated');
                                        }, false);
                                    });
                                }, false);
                            })();
                        </script>
                    </div>
<#--                    <div class="col-md-4">-->
<#--                    </div>-->
                </div>
            </div>
        </div>
    </div>
</div>

<#--表单验证消息-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><span style="color:red">错误：</span></h4>

                <button type="button" onclick="location.reload();" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div id="messageModal" class="modal-body">
                <#if formError??>
                    ${formError}
                </#if>
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

</html>