$(document).ready(function () {
    // tính tổng số page để phân trang
    function getPage(id){
        $.ajax({
            url:"/admin/api/sanpham/trang/"+id,
            type:"get",
            contentType:"application/json",
            success:function (result){
                if (result == 0){
                    Swal.fire({
                        icon: 'error',
                        text: 'Không còn sản phẩm con',
                    })
                } else {
                    pagination(+ result,id)
                    $('#pagination-container').html(`
                     <nav aria-label="Page navigation" class="d-flex justify-content-center">
                                <ul class="pagination" id="pagination"></ul>
                            </nav>
        `)
                }

            }
        })
    }
    //phần trang tống số trang và id sản phẩm chuyển vào
    function pagination(totalPage,id){
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPage,
                visiblePages: 10,
                onPageClick: function (event, page) {
                    $.ajax({
                        url:"/admin/api/sanpham/show/"+id,
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
                                    <td>${e.tenSanPham}</td>
                                    <td><img src="${e.anh}" alt="" srcset="" class="anh-sanpham"></td>
                                    <td>${e.tenLoai}</td>
                                    <td>${e.tenThuongHieu}</td>
                                    <td>${toMoney(e.giaBan)}</td>
                                      <td>${toMoney(e.giaNhap)}</td>
                                    <td>${e.trangThai}</td>
                                    <td>
                                        <div class="d-flex justify-content-center tacvu" data-id="${e.id}">
                                            <button  type="button" class="m-icon-click-to-edit"><i class="far fa-edit"></i></button>
                                            <button type="button" class="m-icon-click-to-delete""><i class="fas fa-trash-alt"></i></button>
                                            <button class="m-icon-click-to-child" type="button"><i class="fas fa-folder-plus"></i></button>
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



    // lấy ra sản phẩm con của id
    $(document).on('click','.m-icon-click-to-child',function (){
        let id = $(this).parents().attr("data-id")
        getPage(id)

    })
    // quay lại trang đầu
    $(document).on('click','.btn-cancel-to-product-previous',function (){
        let id = $(this).parents().attr("data-id")
        getPage(id)

    })
    // click chuyển sang trang sửa
    $(document).on('click',".m-icon-click-to-edit",function (){
        let id = $(this).parents().attr("data-id")
        console.log(id)
        window.location.href = "/admin/product/add/"+id;
    })

// chuyển number -> dạng tiền việt nam
    function toMoney(totalprice){
        return totalprice.toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        });
    }

    getPage("")
    //xoá sản phẩm
    $(document).on('click','.m-icon-click-to-delete',function (){
        let id = $(this).parents().attr("data-id")
        $.ajax({
            url:"/admin/api/sanpham",
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
                        window.location.href = "/admin/product";
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
    })
})