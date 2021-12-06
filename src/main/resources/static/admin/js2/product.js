$(document).ready(function () {
  // TỪ LOẠI SẢN PHẨM CHA GET RA CÁC LOẠI SẢN PHẨM CON
    $('#loaiSPCha').change(function (){
        let id = $(this).val()
        if (id == ""){
            alert("chọn sản phẩm cha")
        } else {
            $.ajax({
                url:"/admin/api/loaisp/"+id,
                type:"get",
                contentType:"application/json",
                success:function (result){
                    let html = `<option value="">Chọn loại sản phẩm</option>`;
                   result.forEach(e=>{

                       html += `   
                         <option value="${e.id}">${e.tenLoaiSP}</option>                     
                        `
                   })
                    $('#loaiSP').html(html)
                }
            })
        }

    })
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
                $('#m-product-img').attr("src",result);
                $('#m-loction-img').val(result);
            },
            error:function (result){

            }

        })
    })

    //ADD Product
    validation({
        form:"#form-them-san-pham",
        error:".errorMessage",
        formGroupSelector:'.form-group',
        button:"#m-btn-add-product",
        rules:[
            validation.isRequired("#tenSanPham","vui lòng nhập tên sản phẩm"),
            validation.isRequired('input[name="trangThai"]',"Chọn trạng trái"),
            validation.isNumber("#giaBan","trường này phải là số"),
            validation.isNumber("#giaNhap","trường này phải là số"),
            validation.isSellThanEntry("#giaNhap",()=>{
                return document.querySelector('#form-them-san-pham #giaBan').value
            } , "giá nhập phải nhỏ hơn giá bán"),
            validation.isRequired("#m-file-anh","vui lòng chọn ảnh cho sản phẩm"),
            validation.isRequired('#loaiSPCha',"Vui lòng chọn loại sản phẩm cha"),
            validation.isRequired('#loaiSP',"Vui lòng chọn loại sản phẩm"),
            validation.isRequired('#thuongHieu',"Vui lòng chọn thương hiệu"),
            validation.isRequired('#sanPhamCha',"Vui lòng chọn sản phẩm cha"),
        ],
        onSubmit : function(data){
            //kiểm tra xem giá nhập vs bán cho khác "" không
            if (data.giaBan == "" && data.giaNhap ==""){
                $('#giaBan').val(0)
                $('#giaNhap').val(0)
            }

            let resutls = {};
            let formSumbit = $('#form-them-san-pham').serializeArray()
            $.each(formSumbit, function (i,v){
                resutls[v.name] = v.value;
            })
            let specialitySelect = document.querySelectorAll(".loaiDacTrung");
            let speciality =[];
            specialitySelect.forEach(e=>{
                if (e.value != "") speciality.push(e.value);
            })
            resutls['loaiDacTrung'] = speciality;
            console.log(resutls)
            $.ajax({
                url:"/admin/api/sanpham",
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
                            window.location.href = "/admin/product/add";
                        }
                    })

                },
                error: function (result){
                    Swal.fire({
                        icon: 'error',
                        text: 'Số lượng phải lớn hơn 0',

                    })
                }
            })
        }

    })
})
