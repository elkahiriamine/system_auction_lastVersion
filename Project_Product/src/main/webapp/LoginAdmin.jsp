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
<br><br>
	<div class="container-fluid">
		<div class="row ">
			<div class="col-8 offset-2 ">
				<div class="card bg-info shadow">
					<div class="card-header ">
						<div class="col-8">
							<h3 class="mb-0 text-white">Login Admin</h3>
						</div>
					</div>
					<div class="card-body bg-info">

						<div class="row">
							<div class="col">
								<form action="verificationLogin.jee" method="post">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">

												<label class="form-control-label">Email:</label> <input
													type="email" name="Email" class="form-control" autofocus="autofocus"/>
											</div>

											<div class="form-group">
												<label class="form-control-label">Password:</label> <input
													type="number" name="password" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<input type="submit" value="Connecter"
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