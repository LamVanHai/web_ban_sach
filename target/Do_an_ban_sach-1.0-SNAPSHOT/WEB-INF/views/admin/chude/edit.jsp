<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/quan-tri/chu-de/danh-sach"/>
<c:url var="editNewURL" value="/quan-tri/chu-de/chinh-sua"/>
<c:url var="categoryAPI" value="/api/category"/>
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
					<form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >Tên chủ đề:</label>
								<div class="col-sm-9">
									<input value="${model.name}" name="name" cssClass="col-xs-10 col-sm-5" placeholder="Văn học"/>
								</div>
						</div>
						<div class="form-group">
						  	<label for="description" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
						  	<div class="col-sm-9">

								<textarea name="description" rows="5" cols="25" cssClass="form-control" id="description">${model.description}</textarea>
						  	</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >Tên đinh danh:</label>
							<div class="col-sm-9">
								<input  cssClass="col-xs-10 col-sm-5" placeholder="van-hoc" name="keyWord" value="${model.keyWord}"/>
							</div>
						</div>
						<input type="hidden" name="id" id="newId" value="${model.id}">
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật chủ đề
									</button>
								</c:if>
								<c:if test="${empty model.id}">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm chủ đề
									</button>
								</c:if>

								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Hủy
								</button>
							</div>		
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>	

<script>
	$('#btnAddOrUpdateNew').click(function (e) {
	    e.preventDefault();
	    var data = {};
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
	
	function addNew(data) {
		$.ajax({
            url: '${categoryAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editNewURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${newURL}?page=1&limit=5&message=error_system";
            }
        });
	}
	
	function updateNew(data) {
		$.ajax({
            url: '${categoryAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editNewURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editNewURL}?id="+error.id+"&message=error_system";
            }
        });
	}
</script>
</body>
</html>
