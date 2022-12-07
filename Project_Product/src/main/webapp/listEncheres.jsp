<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
	<%@include file="headerVendeur.jsp"%>
	<div class="container-fluid" style="text-align: center;">
		<div class="row">
			<div class="col-10 offset-1">
				<div class="card bg-info shadow mb-3" style="padding: -40px;">
					<div class="card-header ourBg">
						<div class="col">
							<h3 class="mb-0 text-white">Liste des Encheres</h3>
						</div>
					</div>
					
					<div class="card-body bg-info table-responsive">
					<nav class="navbar navbar-light bg-info">
						<form action="chercher.jee" method="get" class="form-inline">
							<input class="form-control mr-sm-2" type="text" name="search" value="<%= (request.getParameter("search")==null)? "" : request.getParameter("search") %>"
								placeholder="Search" aria-label="Search">
							<input type="submit" value="Search" class="btn btn-outline-success my-2 my-sm-0 bg-light"
								>
						</form>
					</nav>
						<table class="table table-hover table-bordered">
							<tr class="	table-secondary">
								<th>Nom de produit</th>
								<th>Prix de produit</th>
								<th>Nom de vendeur</th>
								<th>Prenom de vendeur</th>
								<th>Date de debut</th>
								<th>State</th>
								<th colspan="2">Opérations</th>
							</tr>

							<c:forEach var="enchere" items="${listEncheres}">
								<tr>
									<td>${enchere.product.name}</td>
									<td>${enchere.product.prix}</td>
									<td>${enchere.vendeur.firstName}</td>
									<td>${enchere.vendeur.lastName}</td>
									<td>${enchere.dateDebut}</td>
									<td>${enchere.etat}</td>
									<td class="iconDelete"><a class="text-danger"
										onclick="return confirm('êtes-vous sûr?')"
										href="supprime.jee?codeE=${product.id}">Supprimer</a></td>
									<td class="iconEdit"><a class="text-warning"
										href="modifie.jee?codeE=${product.id}">Modifier</a></td>
								</tr>
							</c:forEach>

						</table>
					</div>
					<div class="card-footer ourBg">
						<form method="get" action="ajouteProduct.jee">
							<div class="row">
								<div class="col-md-6 offset-3">
									<div class="form-group">
										<button type="submit" class="btn btn-success btn-block btn-sm">Ajouter
											Product</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>