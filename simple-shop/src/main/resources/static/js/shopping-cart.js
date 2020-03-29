// ************************************************
// Shopping Cart API
// ************************************************

var shoppingCart = (function () {
    // =============================
    // Private methods and properties
    // =============================
    cart = [];

    // Constructor
    function Item(brand, model, image, price, count) {
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.price = price;
        this.count = count;
    }

    // Save cart
    function saveCart() {
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    }

    // Load cart
    function loadCart() {
        cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    }

    if (sessionStorage.getItem("shoppingCart") != null) {
        loadCart();
    }


    // =============================
    // Public methods and properties
    // =============================
    var obj = {};

    // Add to cart
    obj.addItemToCart = function (brand, model, image, price, count) {
        brand = returnWhitespaces(brand);
        model = returnWhitespaces(model);
        for (let item in cart) {
            if (cart[item].brand === brand && cart[item].model === model) {
                cart[item].count++;
                saveCart();
                return;
            }
        }
        let item = new Item(brand, model, image, price, count);
        cart.push(item);
        saveCart();
    };
    // Set count from item
    obj.setCountForItem = function (brand, model, count) {
        for (const item in cart) {
            if (cart[item].brand === brand && cart[item].model === model) {
                cart[item].count = count;
                break;
            }
        }
    };
    // Remove item from cart
    obj.removeItemFromCart = function (brand, model) {
        brand = returnWhitespaces(brand);
        model = returnWhitespaces(model);
        for (const item in cart) {
            if (cart[item].brand === brand && cart[item].model === model) {
                if (cart[item].count > 1) {
                    cart[item].count--;
                } else {
                    break;
                }
                break;
            }
        }
        saveCart();
    };

    // Remove all items from cart
    obj.removeItemFromCartAll = function (brand, model) {
        for (const item in cart) {
            brand = returnWhitespaces(brand);
            model = returnWhitespaces(model);
            if (cart[item].brand === brand && String(cart[item].model) === model) {
                cart.splice(item, 1);
                break;
            }
        }
        saveCart();
    };

    // Clear cart
    obj.clearCart = function () {
        cart = [];
        saveCart();
    };

    // Count cart
    obj.totalCount = function () {
        let totalCount = 0;
        for (const item in cart) {
            totalCount += cart[item].count;
        }
        return totalCount;
    };

    // Total cart
    obj.totalCart = function () {
        let totalCart = 0;
        for (const item in cart) {
            totalCart += cart[item].price * cart[item].count;
        }
        return Number(totalCart.toFixed(2));
    };

    // List cart
    obj.listCart = function () {
        var cartCopy = [];
        for (let i in cart) {
            item = cart[i];
            itemCopy = {};
            for (p in item) {
                itemCopy[p] = item[p];
            }
            itemCopy.total = Number(item.price * item.count).toFixed(2);
            cartCopy.push(itemCopy)
        }
        return cartCopy;
    };
    return obj;
})();


// *****************************************
// Triggers / Events
// ***************************************** 
// Add item
$('.add-to-cart').click(function (event) {
    event.preventDefault();
    var brand = $(this).data('brand');
    var model = $(this).data('model');
    var image = $(this).data('image');
    var price = Number($(this).data('price'));
    shoppingCart.addItemToCart(brand, model, image, price, 1);
    displayCart();
});

// Clear items
$('.clear-cart').click(function () {
    shoppingCart.clearCart();
    displayCart();
});


function displayCart() {
    const cartArray = shoppingCart.listCart();
    let output = "";
    for (const item in cartArray) {
        const brand = removeWhitespaces(cartArray[item].brand);
        const model = removeWhitespaces(cartArray[item].model);
        const price = cartArray[item].price;
        output += "<tr>"
            + "<td>" + cartArray[item].brand + " " + cartArray[item].model + "</td>"
            + "<td>(" + price + ")</td>"
            + "<td><div class='input-group'><button class='minus-item input-group-addon btn btn-primary' data-brand=" + brand + " data-model=" + model + ">-</button>"
            + "<input type='number' class='item-count form-control' data-brand=" + brand + " data-model=" + model + "' value='" + cartArray[item].count + "'>"
            + "<button class='plus-item btn btn-primary input-group-addon' data-brand=" + brand + " data-model=" + model + ">+</button></div></td>"
            + "<td><button class='delete-item btn btn-danger' data-brand=" + brand + " data-model=" + model + ">X</button></td>"
            + " = "
            + "<td>" + cartArray[item].total + "</td>"
            + "</tr>";
    }
    $('.show-cart').html(output);
    $('.total-cart').html(shoppingCart.totalCart());
    $('.total-count').html(shoppingCart.totalCount());

}

// Delete item button

$('.show-cart').on("click", ".delete-item", function (event) {
    let brand = $(this).data('brand');
    let model = $(this).data('model');
    shoppingCart.removeItemFromCartAll(brand, model);
    displayCart();
});

// -1
$('.show-cart').on("click", ".minus-item", function (event) {
    let brand = $(this).data('brand');
    let model = $(this).data('model');
    shoppingCart.removeItemFromCart(brand, model);
    displayCart();
});
// +1
$('.show-cart').on("click", ".plus-item", function (event) {
    let brand = $(this).data('brand');
    let model = $(this).data('model');
    shoppingCart.addItemToCart(brand, model);
    displayCart();
});

// Item count input
$('.show-cart').on("change", ".item-count", function (event) {
    const brand = $(this).data('brand');
    const model = $(this).data('model');
    const count = Number($(this).val());
    shoppingCart.setCountForItem(brand, model, count);
    displayCart();
});

function removeWhitespaces(str) {
    return String(str).replace(/\s/g, "_");
}

function returnWhitespaces(str) {
    return String(str).replace(/_/g, " ")
}

displayCart();