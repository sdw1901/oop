<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th style="text-align:center">类目id</th>
                            <th style="text-align:center">名字</th>
                            <th style="text-align:center">type</th>
                            <th style="text-align:center">创建时间</th>
                            <th style="text-align:center">修改时间</th>
                            <th style="text-align:center">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryList as category>
                        <tr>
                            <td style="text-align:center">${category.categoryId}</td>
                            <td style="text-align:center">${category.categoryName}</td>
                            <td style="text-align:center">${category.categoryType}</td>
                            <td style="text-align:center">${category.createTime}</td>
                            <td style="text-align:center">${category.updateTime}</td>
                            <td style="text-align:center"><a href="/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>