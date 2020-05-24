// ************************************************
// Shopping Cart API
// ************************************************

const shoppingCart = (function () {
    // =============================
    // Private methods and properties
    // =============================
    cart = [];

    // Constructor
    function Item(brand, model, image, price, quantity, category) {
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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
    let obj = {};

    // Add to cart
    obj.addItemToCart = function (brand, model, image, price, quantity, category) {
        brand = returnWhitespaces(brand);
        model = returnWhitespaces(model);
        for (let item in cart) {
            if (cart[item].brand === brand && cart[item].model === model) {
                cart[item].quantity++;
                saveCart();
                return;
            }
        }
        let item = new Item(brand, model, image, price, quantity, category);
        cart.push(item);
        saveCart();
    };
    // Set quantity from item
    obj.setCountForItem = function (brand, model, quantity) {
        for (const item in cart) {
            if (cart[item].brand === brand && cart[item].model === model) {
                cart[item].quantity = quantity;
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
                if (cart[item].quantity > 1) {
                    cart[item].quantity--;
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
            totalCount += cart[item].quantity;
        }
        return totalCount;
    };

    // Total cart
    obj.totalCart = function () {
        let totalCart = 0;
        for (const item in cart) {
            totalCart += cart[item].price * cart[item].quantity;
        }
        return Number(totalCart.toFixed(2));
    };

    // List cart
    obj.listCart = function () {
        let cartCopy = [];
        for (let i in cart) {
            let item = cart[i];
            let itemCopy = {};
            for (let p in item) {
                itemCopy[p] = item[p];
            }
            itemCopy.total = Number(item.price * item.quantity).toFixed(2);
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
    const brand = $(this).data('brand');
    const model = $(this).data('model');
    const image = $(this).data('image');
    const price = Number($(this).data('price'));
    const quantity = 1;
    const category = $(this).data('category');
    shoppingCart.addItemToCart(brand, model, image, price, quantity, category);
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
            + "<input type='number' class='item-count form-control' data-brand=" + brand + " data-model=" + model + "' value='" + cartArray[item].quantity + "'>"
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

// Item quantity input
$('.show-cart').on("change", ".item-count", function (event) {
    const brand = $(this).data('brand');
    const model = $(this).data('model');
    const quantity = Number($(this).val());
    shoppingCart.setCountForItem(brand, model, quantity);
    displayCart();
});

function removeWhitespaces(str) {
    return String(str).replace(/\s/g, "_");
}

function returnWhitespaces(str) {
    return String(str).replace(/_/g, " ")
}

displayCart();

const getCircularReplacer = () => {
    const seen = new WeakSet();
    return (key, value) => {
        if (typeof value === "object" && value !== null) {
            if (seen.has(value)) {
                return;
            }
            seen.add(value);
        }
        return value;
    };
};


$("#btn-order").click((async () => {

    let order = {};
    order.firstName = document.getElementById("first-name").value;
    order.lastName = document.getElementById("last-name").value;
    order.streetAddress = document.getElementById("street-address").value;
    order.city = document.getElementById("city").value;
    order.postcode = document.getElementById("postcode").value;
    order.phone = document.getElementById("phone").value;
    order.email = document.getElementById("email").value;
    const shoppingCart = JSON.parse(sessionStorage.getItem("shoppingCart"));
    order.orderItems = shoppingCart;
    order.orderItems.forEach(item => {
        item.item = {};
        item.item.brand = item.brand;
        item.item.model = item.model;
        item.item.price = item.price;
        item.item.imageUrl = item.image;
        item.item.quantity = Number(item.quantity);
        item.item.category = item.category;
        item.order = {};
        item.order = order;
    });
    order.created = new Date();
    order.finished = false;
    order.amount = shoppingCart.reduce((acc, cur) => {
        return acc + cur.price * cur.quantity;
    }, 0);

    const rawResponse = await fetch('http://localhost:8080/order', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order, getCircularReplacer())
    });
    // const content = await rawResponse.json();
}));
