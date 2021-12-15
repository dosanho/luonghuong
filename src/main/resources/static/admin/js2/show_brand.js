$(document).ready(function () {
    // tính tổng số page để phân trang
        $.ajax({
            url:"/admin/api/brand/total",
            type:"get",
            contentType:"application/json",
            success:function (result){
                pagination(+ result)

            }
        })
    //
    // phần trang tống số trang và id sản phẩm chuyển vào
    function pagination(totalPage){
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPage,
                visiblePages: 10,
                onPageClick: function (event, page) {
                    $.ajax({
                        url:"/admin/api/brand",
                        type:"get",
                        contentType:"application/json",
                        dataType:'Json',
                        data:{page},
                        success:function (result){
                            console.log(result)
                            let index = 1;
                            let html = '';
                            result.forEach(e=>{
                                html +=`
                                <tr>
                                    <th scope="row"><input type="checkbox" name="chonsanpham"></th>
                                    <th scope="row">${index}</th>
                                     <td>${e.tenThuongHieu}</td>
                                     <td>${e.moTa}</td>
                                    <td><img src="${e.hinhAnh}" alt="" srcset="" class="anh-sanpham"></td>                            
                                    <td>${e.trangThai}</td>
                                    <td>
                                        <div class="d-flex justify-content-center tacvu" data-id="${e.id}">
                                            <button  type="button" class="m-icon-click-to-edit"><i class="far fa-edit"></i></button>
                                            <button type="button" class="m-icon-click-to-delete""><i class="fas fa-trash-alt"></i></button>
                                        </div>
                                    </td>
                                </tr>`
                                index++
                            })
                            $('#list-show-product').html(html);
                        }
                    })
                }
            })
        });
    }


    
    // click chuyển sang trang sửa
    $(document).on('click',".m-icon-click-to-edit",function (){
        let id = $(this).parents().attr("data-id")
        console.log(id)
        window.location.href = "/admin/brand/add/"+id;
    })


    //xoá sản phẩm
    $(document).on('click','.m-icon-click-to-delete',function (){
        let id = $(this).parents().attr("data-id")
        Swal.fire({
            title: 'Bạn chắc chắn muốn xoá không?',
            icon: 'warning',
            showCancelButton: true,

            confirmButtonText: 'Có'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url:"/admin/api/brand",
                    type:"delete",
                    contentType:"application/json",
                    dataType:"json",
                    data:JSON.stringify(id),
                    success: function(results){
                        Swal.fire(
                            'xoá thành công!',
                            '',
                            'success'
                        ).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "/admin/brand";
                            }
                        })

                    },
                    error: function (result){
                        Swal.fire({
                            icon: 'error',
                            text: 'xoá thất bại',

                        })
                    }
                })
                Swal.fire(
                    'Xoá thành công!',
                    '',
                    'success'
                )
                getPage(id)
            }
        })
    })
})

