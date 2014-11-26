<!DOCTYPE html>
<html class="no-js modern" lang="de">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	if (!library)
		var library = {};

	library.json = {
		replacer : function(match, pIndent, pKey, pVal, pEnd) {
			var key = '<span class=json-key>';
			var val = '<span class=json-value>';
			var str = '<span class=json-string>';
			var r = pIndent || '';
			if (pKey)
				r = r + key + pKey.replace(/[": ]/g, '') + '</span>: ';
			if (pVal)
				r = r + (pVal[0] == '"' ? str : val) + pVal + '</span>';
			return r + (pEnd || '');
		},
		prettyPrint : function(obj) {
			var jsonLine = /^( *)("[\w]+": )?("[^"]*"|[\w.+-]*)?([,[{])?$/mg;
			return JSON.stringify(obj, null, 3).replace(/&/g, '&amp;').replace(
					/\\"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g,
					'&gt;').replace(jsonLine, library.json.replacer);
		}
	};
	$(function() {
		var token = (window.location.href).substring((window.location.href)
				.lastIndexOf("token="));
		token = token.replace("token", "jsessionid");

		$("#toggleStatus").click(
				function() {
					if (!$("#statusOfPayment").is(":visible")) {
						$("#statusOfPayment").slideToggle();
						$("#statusOfPayment").load(
								"https://test.ctpe.net/frontend/GetStatus;"
										+ token,
								function() {
									var json = $("#statusOfPayment").html();
									$('#statusOfPayment').html(
											library.json.prettyPrint($
													.parseJSON(json)));
								});

					}
				})
	})
</script>
<link href="bundle.css" rel="stylesheet" type="text/css">
<style>
#toggleStatus {
	cursor: pointer;
}

#statusOfPayment {
	display: none;
}
</style>



</head>
<body id="payment">
	<header id="pagetop" class="type-2">
		<div class="container_12">
			<div class="grid_12 clearfix masthead">

				<p class="hd-welcome">
					<strong>Hello&nbsp;Hans</strong> (<a id="logoutLink" href="#">Logout</a>)
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

				<section>
					<h2>Your payment was successful! Thank you for shopping.</h2>
				</section>
			</div>
			<div class="grid_3 aside">
				<p class="hint">Goods will be shipped to</p>
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
