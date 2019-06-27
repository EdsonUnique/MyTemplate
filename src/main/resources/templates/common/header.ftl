<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-dark bg-dark static-top">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="navbar-toggler-icon"></span>
    </button> <a class="navbar-brand" href="/sell/sellOrder/view">卖家管理系统</a>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">订单 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">商品类别</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">商品</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/sell/product/addOrUpdateUI">新增</a>
                    <div class="dropdown-divider">
                    </div> <a class="dropdown-item" href="/sell/product/list">查看所有</a>
                </div>
            </li>
        </ul>
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="text" />
            <button class="btn btn-primary my-2 my-sm-0" type="submit">
                搜索
            </button>
        </form>
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/sell/seller/logout">退出 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">个人中心</a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
                    <div class="dropdown-divider">
                    </div> <a class="dropdown-item" href="#">Separated link</a>
                </div>
            </li>
        </ul>
    </div>
</nav>