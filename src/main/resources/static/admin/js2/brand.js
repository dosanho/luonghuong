$(document).ready(function () {
    // lấy dữ liệu trong ckeditor
    var moTa = '';
    $(document).ready(function(){
        moTa = CKEDITOR.replace('moTa');
    });
    // LƯU ẢNH
    $("#m-file-anh").change(function (){

        let img = $(this).prop('files')[0];
        let baseUrl =  $('#m-loction-img').val();
        let data = new FormData();
        data.append('img', img);
        data.append('baseUrl', baseUrl);
        $.ajax({
            type:"post",
            url:"/admin/api/img",
            data:data,
            cache:false,
            contentType: false,
            processData:false,
            success:function (result){
                $('#m-brand-img').attr("src",result);
                $('#m-loction-img').val(result);
            },
            error:function (result){

            }

        })
    })

    //ADD Product
    validation({
        form:"#form-thuong-hieu",
        error:".errorMessage",
        formGroupSelector:'.form-group',
        button:"#m-btn-add-product",
        rules:[
            validation.isRequired("#tenThuongHieu","vui lòng nhập tên thương hiệu"),
            validation.isRequired('input[name="trangThai"]',"Chọn trạng trái"),
            validation.isRequired("#m-loction-img","vui lòng chọn ảnh cho thương hiệu"),

        ],
        onSubmit : function(data){
            data["moTa"] = moTa.getData()

            if (data['id']){
                suaThuongHieu(data)
            } else {
                themThuongHieu(data)
            }
        }

    })

    // thêm , sửa sản phẩm ajax của jquery
    function themThuongHieu(resutls){
        $.ajax({
            url:"/admin/api/brand",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(resutls),
            success: function(results){
                Swal.fire(
                    'Thêm thành công!',
                    '',
                    'success'
                ).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/admin/brand/add";
                    }
                })
            },
            error: function (result){
                Swal.fire({
                    icon: 'error',
                    text: 'thêm thất bại',

                })
            }
        })
    }
    function suaThuongHieu(resutls){
        $.ajax({
            url:"/admin/api/brand",
            type:"put",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(resutls),
            success: function(results){
                Swal.fire(
                    'sửa thành công!',
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
                    text: 'sửa thất bại',

                })
            }
        })
    }

    //click quay lại trang danh sách sản phẩm
    $('.click-cancel').click(function (){
        window.location.href = "/admin/brand";
    })
})
