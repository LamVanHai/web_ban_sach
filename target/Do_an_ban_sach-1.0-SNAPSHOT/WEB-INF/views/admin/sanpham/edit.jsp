<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua"/>
<c:url var="bookAPI" value="/api/book"/>
<c:url var="uploadFileAPI" value="/api/bookimage"/>
<html>
<head>
<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
				</li>

				<li><a href="#">Forms</a></li>
				<li class="active">Form Elements</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
  							${message}
						</div>
					</c:if>
					<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
						<div class="form-group">
							<label for="publisher_id" class="col-sm-3 control-label no-padding-right">Thể nhà sản xuất:</label>
							<div class="col-sm-9">
								<form:select path="publisher_id" id="publisher_id">
									<form:option value="" label="-- Chọn nhà xuất bản--"/>
									<form:options items="${publisher}"/>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							  <label for="category_id" class="col-sm-3 control-label no-padding-right">Thể loại:</label>
							  <div class="col-sm-9">
							  	 <form:select path="category_id" id="category_id">
							  	 	<form:option value="" label="-- Chọn thể loại --"/>
							  	 	<form:options items="${categories}"/>
							  	 </form:select>
							  </div>
						</div>
						<div class="form-group">
							<label for="category_id" class="col-sm-3 control-label no-padding-right">Tác giả:</label>
							<div class="col-sm-9">
								<form:select path="writer_id" id="category_id">
									<form:option value="" label="-- Chọn tác giả--"/>
									<form:options items="${writer}"/>
								</form:select>
							</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >Tên sách:</label>
								<div class="col-sm-9">
									<form:input path="name"  cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Ảnh đại diện</label>
								<div class="col-sm-9">
									<input  type="file" value="${model.image}" class="col-xs-10 col-sm-5" id="image" onchange="uploadFileImage()"  />
								</div>
						</div>
						<div class="form-group">
						  	<label for="description" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
						  	<div class="col-sm-9">
						  		<form:textarea path="description" rows="5" cols="10" cssClass="form-control" id="description"/>
						  	</div>
<%--							<script>--%>
<%--								CKEDITOR.replace('description');--%>
<%--							</script>--%>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >Giá nhập hàng:</label>
							<div class="col-sm-9">
								<form:input path="input_price" cssClass="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >Giá:</label>
							<div class="col-sm-9">
								<form:input path="price" cssClass="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >Giá sale:</label>
							<div class="col-sm-9">
								<form:input path="sale_price" cssClass="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >Số lượng:</label>
							<div class="col-sm-9">
								<form:input path="input_amount" cssClass="col-xs-10 col-sm-5"/>
							</div>
						</div>

						<form:hidden path="id" id="newId"/>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật sản phẩm
									</button>
								</c:if>
								<c:if test="${empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm sản phẩm
									</button>
								</c:if>&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Hủy
								</button>
							</div>		
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>	

<script>
	$('#btnAddOrUpdateNew').click(function (e) {
	    e.preventDefault();
		var data = {
		};
	    var formData = $('#formSubmit').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    var id = $('#newId').val();
	    if (id == "") {
	    	addNew(data);
	    } else {
	    	updateNew(data);
	    }
	});
	function uploadFileImage() {
		var dataArray={};
		var files=$('#image')[0].files[0];
		if(files != undefined){
			var reader = new FileReader();
			reader.onload=function (e) {
				dataArray["base64"]=e.target.result;
				dataArray["name"]=files.name;
				uploadFile(dataArray);
			};
			reader.readAsDataURL(files);
		}
	}
	function uploadFile(data) {
		$.ajax({
			url: '${uploadFileAPI}',
			type:'POST',
			data:JSON.stringify(data),
			contentType:'application/json',
			success:function (res) {
			},
			error:function (res) {
			}
		});
	}
	function addNew(data) {
		$.ajax({
            url: '${bookAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editNewURL}?id="+result.id+"&search=all&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${newURL}?page=1&search=all&message=error_system";
            }
        });
	}
	
	function updateNew(data) {
		$.ajax({
            url: '${bookAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editNewURL}?id="+result.id+"&search=all&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editNewURL}?id="+error.id+"&search=all&message=error_system";
            }
        });
	}
</script>
</body>
</html>
