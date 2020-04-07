function loadItems() {
    const items = JSON.parse(sessionStorage.getItem("shoppingCart"));
    let totalPrice = 0;
    let output = "<tr>"
        + "<th class='font-weight-bold h5'>Product</th>"
        + "<th class='font-weight-bold h5'>Count</th>"
        + "<th class='font-weight-bold h5'>Price</th>"
        + "</tr>";
    items.forEach(function (item) {
        const brand = item.brand;
        const model = item.model;
        const count = Number(item.count);
        const price = Number(item.price);
        totalPrice += Number(count * price);
        output +=
            "<tr>"
            + "<td>" + brand + " " + model + "</td>"
            + "<td>" + "x " + count + "</td>"
            + "<td>" + "$" + price * count + "</td>"
            + "</tr>"

    });

    output += "<tr>"
        + "<td class='font-weight-bold h5' colspan='2'>" + "Total price:" + "</td>"
        + "<td class='font-weight-bold h5'>" + "$" + Number(totalPrice).toFixed(2) + "</td>"
        + "</tr>"


    $("#Yorder table").html(output)
}

loadItems()