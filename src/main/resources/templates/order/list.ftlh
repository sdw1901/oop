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
                            <th style="text-align:center">订单id</th>
                            <th style="text-align:center">姓名</th>
                            <th style="text-align:center">手机号</th>
                            <th style="text-align:center">地址</th>
                            <th style="text-align:center">金额</th>
                            <th style="text-align:center">订单状态</th>
                            <th style="text-align:center">支付状态</th>
                            <th style="text-align:center">创建时间</th>
                            <th style="text-align:center" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderDTOPage.list as orderDTO>
                        <tr>
                            <td style="text-align:center">${orderDTO.orderId}</td>
                            <td style="text-align:center">${orderDTO.buyerName}</td>
                            <td style="text-align:center">${orderDTO.buyerPhone}</td>
                            <td style="text-align:center">${orderDTO.buyerAddress}</td>
                            <td style="text-align:center">${orderDTO.orderAmount}</td>
                            <td style="text-align:center">${orderDTO.orderStatusEnum.message}</td>
                            <td style="text-align:center">${orderDTO.payStatusEnum.message}</td>
                            <td style="text-align:center">${orderDTO.createTime}</td>
                            <td style="text-align:center"><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td style="text-align:center">
                                <#if orderDTO.orderStatus == 0>
                                    <a href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                        <li><a href="/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..orderDTOPage.pages as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.pages>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

<#--&lt;#&ndash;弹窗&ndash;&gt;-->
<#--<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
<#--    <div class="modal-dialog">-->
<#--        <div class="modal-content">-->
<#--            <div class="modal-header">-->
<#--                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
<#--                <h4 class="modal-title" id="myModalLabel">-->
<#--                    提醒-->
<#--                </h4>-->
<#--            </div>-->
<#--            <div class="modal-body">-->
<#--                你有新的订单-->
<#--            </div>-->
<#--            <div class="modal-footer">-->
<#--                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>-->
<#--                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

<#--&lt;#&ndash;播放音乐&ndash;&gt;-->
<#--<audio id="notice" loop="loop">-->
<#--    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />-->
<#--</audio>-->

<#--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>-->
<#--<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->
<#--<script>-->
<#--    var websocket = null;-->
<#--    if('WebSocket' in window) {-->
<#--        websocket = new WebSocket('ws://sell.natapp4.cc/sell/webSocket');-->
<#--    }else {-->
<#--        alert('该浏览器不支持websocket!');-->
<#--    }-->

<#--    websocket.onopen = function (event) {-->
<#--        console.log('建立连接');-->
<#--    }-->

<#--    websocket.onclose = function (event) {-->
<#--        console.log('连接关闭');-->
<#--    }-->

<#--    websocket.onmessage = function (event) {-->
<#--        console.log('收到消息:' + event.data)-->
<#--        //弹窗提醒, 播放音乐-->
<#--        $('#myModal').modal('show');-->

<#--        document.getElementById('notice').play();-->
<#--    }-->

<#--    websocket.onerror = function () {-->
<#--        alert('websocket通信发生错误！');-->
<#--    }-->

<#--    window.onbeforeunload = function () {-->
<#--        websocket.close();-->
<#--    }-->

<#--</script>-->

</body>
</html>