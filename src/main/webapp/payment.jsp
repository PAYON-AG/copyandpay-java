<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js modern" lang="de">
<head>
<link href="bundle.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="https://test.ctpe.net/frontend/widget/v3/widget.js?language=en&style=card">
</script>
</head>
<body id="payment">
<%
    String token = (String)request.getAttribute("token");
%>
	<header id="pagetop" class="type-2">
		<div class="container_12">
			<div class="grid_12 clearfix masthead">


				<p class="hd-welcome">
					<strong>Hello&nbsp;Hans</strong> (<a id="logoutLink"
						href="https://www.brands4friends.de/account/logout">Logout</a>)
				</p>

				<p class="time t-help-trigger" id="basketTimer" data-time="893"></p>
			</div>
			<div class="clear"></div>
		</div>
	</header>
	<nav>
		<div class="container_12">
			<div class="grid_12">
				<ul class="steps clearfix">
					<li class="first">1. Your shopping basket</li>
					<li>2. Your address</li>
					<li class="last current">3. Your payment</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="main">
		<div class="container_12">
			<div class="grid_8 suffix_1">
				<div class="cart clearfix">
					<div class="cart-content">
						<h2>Shopping basket</h2>
						<table>
							<thead>
								<tr>
									<th colspan="3">Article</th>
									<th class="last">Price</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td class="image"><img
										src="https://static.brands4friends.de/120813PRCU/wk/7630003636598_001.jpg"
										alt="Schultertasche Filatura, B40 x H45 x T20 cm" /></td>
									<td class="description"><strong>PRINCESS&amp;CULT</strong>
										Should bag Filatura, B40 x H45 x T20 cm<span class="uvp">
											<abbr>UVP</abbr> <del>129,00&nbsp;&euro;</del> <strong>44,90&nbsp;&euro;</strong>
									</span></td>
									<td class="meta">Amount:&nbsp;1<br />
									</td>
									<td class="price">44,90&nbsp;&euro;</td>
								</tr>
							</tbody>
						</table>
					</div>
					<table class="result">
						<tbody>
							<tr>
								<th scope="row">Sum</th>
								<td>44,90&nbsp;&euro;</td>
							</tr>
							<tr>
								<th scope="row">Shipping costs to DE</th>
								<td>6,90&nbsp;&euro;</td>
							</tr>
							<tr class="final">
								<th scope="row">Sum of your order (<abbr>inkl.</abbr> <abbr>MwSt.</abbr>)
								</th>
								<td>51,80&nbsp;&euro;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<section>
					<h2>Payment details</h2>

					<h3>How do you you want to pay?</h3>

					<!-- put the payment form here -->
					<form action="http://localhost:8080/mavenServlet/status" id="<%=token%>">visa master</form>

				</section>
			</div>
			<div class="grid_3 aside">
				<p class="hint">Please check your data</p>
				<section class="address">
					<h3>Shipping address</h3>
					<address>
						Hans Debtor<br /> Drehbahn 49<br /> 20354 Hamburg<br />
						Deutschland<br />
					</address>
					<p>
						<a
							href="/checkout/products?execution=e1s2&amp;_eventId=CHANGE_SHIPPING_ADDRESS">&Auml;ndern</a>
					</p>
				</section>
				<section class="address">
					<h3>Billing address</h3>
					<address>
						Hans Debtor<br /> Drehbahn 49<br /> 20354 Hamburg<br />
						Deutschland
					</address>
					<p>
						<a
							href="/checkout/products?execution=e1s2&amp;_eventId=CHANGE_BILLING_ADDRESS">&Auml;ndern</a>
					</p>
				</section>
				<section>
					<h3>Overview</h3>
					<table>
						<tbody>
							<tr>
								<th scope="row">Article</th>
								<td>44,90&nbsp;&euro;</td>
							</tr>
							<tr>
								<th scope="row">Shipping costs</th>
								<td>6,90&nbsp;&euro;</td>
							</tr>
							<tr class="result">
								<th scope="row">Sum of your order</th>
								<td>51,80&nbsp;&euro;</td>
							</tr>
						</tbody>
					</table>
				</section>
			</div>
			<div class="clear"></div>
			<div class="grid_8 suffix_4"></div>
		</div>
	</div>
</body>
</html>
