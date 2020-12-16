<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <form class="navbar-form navbar-left" role="role" method="get" action="/seller/product/list">
        <div class="form-group">
            <select id="categoryType" name="categoryType" class="form-control">
                <#list categoryList as category>
                    <option value="${category.categoryType}"
                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                selected
                            </#if>
                    >${category.categoryName}
                    </option>
                </#list>
            </select>
        </div>
        <button type="submit" class="btn btn-default">筛选</button>
    </form>
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th style="text-align:center">商品id</th>
                            <th style="text-align:center">名称</th>
                            <th style="text-align:center">图片</th>
                            <th style="text-align:center">单价(元)</th>
                            <th style="text-align:center">库存</th>
                            <th style="text-align:center">描述</th>
                            <th style="text-align:center">类目</th>
                            <th style="text-align:center">创建时间</th>
                            <th style="text-align:center">修改时间</th>
                            <th style="text-align:center" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPage.list as productInfo>
                        <tr>
                            <td style="text-align:center">${productInfo.productId}</td>
                            <td style="text-align:center">${productInfo.productName}</td>
                            <td style="text-align:center"><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td style="text-align:center">${productInfo.productPrice}</td>
                            <td style="text-align:center">${productInfo.productStock}</td>
                            <td style="text-align:center">${productInfo.productDescription}</td>
                            <td style="text-align:center">${productInfo.categoryType}</td>
                            <td style="text-align:center">${productInfo.createTime}</td>
                            <td style="text-align:center">${productInfo.updateTime}</td>
                            <td style="text-align:center"><a href="/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td style="text-align:center">
                                <#if productInfo.getProductStatusEnum().message == "在架">
                                    <a href="/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..productInfoPage.pages as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.pages>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>