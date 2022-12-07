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
	<%@include file="headerVendeur.jsp"%>
	<div class="container-fluid">
		<div class="row ">
			<div class="col-8 offset-2 ">
				<div class="card bg-info shadow">
					<div class="card-header ">
						<div class="col-8">
							<h3 class="mb-0 text-white">Édition de Enchere</h3>
						</div>
					</div>
					<div class="card-body bg-info">

						<div class="row">
							<div class="col">
								<form action="ajouteEnchere.jee" method="post">
								<input type="hidden" name="state" value="started" />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">

												<label class="form-control-label">Nom de Produit:</label> <input
													type="text" name="name" class="form-control"
													autofocus="autofocus" />
											</div>

											<div class="form-group">
												<label class="form-control-label">Prix de Produit:</label> <input
													type="number" name="prix" class="form-control" />
											</div>

											<div class="form-group">
												<label class="form-control-label">Date de Debut:</label> <input
													type="date" name="date_debut" class="form-control"
													value="2022-01-01" min="2022-01-01" max="2023-12-31" />
											</div>

											<!-- <div class="form-group">
												<label class="form-control-label">Date de Fin:</label> <input
													type="date" name="date_fin" class="form-control"
													value="2022-01-01" min="2022-01-01" max="2023-12-31" />
											</div> -->

										</div>
									</div>
									<div class="row">
										<div class="col-md-6 offset-5">
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