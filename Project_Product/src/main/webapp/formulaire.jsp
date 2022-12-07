<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
	<%@include file="headerAcheteur.jsp"%>
	<div class="container-fluid">
		<div class="row ">
			<div class="col-8 offset-2 ">
				<div class="card bg-info shadow">
					<div class="card-header ">
						<div class="col-8">
							<h3 class="mb-0 text-white">Édition de Produit</h3>
						</div>
					</div>
					<div class="card-body bg-info">

						<div class="row">
							<div class="col">
								<form action="updateProduct.jee" method="post">
									<input type="hidden" name="idProduct" value='${enchere.product.id}' />
									<input type="hidden" name="CodeVendeur" value='${enchere.product.code}' />
									<input type="hidden" name="CodeEnchere" value='${enchere.id}' />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">

												<label class="form-control-label">Nom:</label> <input
													type="text" name="name" class="form-control"
													autofocus="autofocus" value='${enchere.product.name}' />
											</div>

											<div class="form-group">
												<label class="form-control-label">Prix:</label> <input
													type="number" name="prix" class="form-control"
													value='${enchere.product.prix}' />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 offset-1">
											<input type="submit" value="Enregistrer"
												class="btn btn-block btn-sm btn-success">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>