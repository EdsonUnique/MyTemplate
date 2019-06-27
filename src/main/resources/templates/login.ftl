<html>
    <head>
        <title>登录</title>
        <#include "common/bootstrapCss.ftl">
    </head>
    <body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <#include "common/header.ftl">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <form method="post" class="needs-validation" role="form" novalidate action="/sell/seller/login">
                                <div class="form-row">
                                    <div class="col-md-8 mb-8">
                                        <label for="validationCustom01">微信号</label>
                                        <input type="text" class="form-control" name="openid" id="validationCustom01"  value="" required>
                                        <div class="invalid-feedback">
                                            微信号不能为空！
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary" type="submit">登录</button>

                            </form>
                        </div>
                        <div class="col-md-4">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </body>
</html>