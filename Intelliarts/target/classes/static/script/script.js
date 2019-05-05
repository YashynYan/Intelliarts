let api_key = "5a8b26f5469cd6a79adcb18530c27cc3";
let mainUrl = "http://localhost:8080";

getAllCurrencies();
setActionOnSubmitButton();
setActionOnGetAllButton();
setActionOnDeleteButton();
setActionOnReportButton()



function getAllCurrencies() {
    $.ajax({
        url: "http://data.fixer.io/api/symbols?access_key=" + api_key,
        dataType: 'jsonp',
        success: function (json) {
           appendAllCurrencies(json);
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function appendAllCurrencies(json) {
    let list = $("#currencyDrop");
    let reportlist = $("#currencyReportDrop");
    let arr ={};
    arr = json.symbols;
    for (var i = 0; i < Object.keys(arr).length; i++) {
        list.append(`<option value="` + Object.keys(arr)[i] + `">` + Object.keys(arr)[i] + `</option>`);
        reportlist.append(`<option value="` + Object.keys(arr)[i] + `">` + Object.keys(arr)[i] + `</option>`);
    }
}

function setActionOnSubmitButton() {
    $("#submit-button").click(function () {
        var currencyName = $('#currencyDrop').val();
        var date = $('#date').val();
        var money = $('#amount').val();
        var name = $('#name').val();
        var purchase = {
            "currencyName": currencyName,
            "date": date,
            "money": money,
            "name": name
        };
        post(purchase);
        appendPurchase(response);
    })

}

function post (purchase){
    $.ajax({
        url: mainUrl + "/product/save",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(purchase),
        success: function (response) {
            location.reload();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function appendPurchase(product) {
    var products=[];
    products.push(product);
    products.sort(function(a, b) {
        var dateA = new Date(a.date), dateB = new Date(b.date);
        return dateA - dateB;});
    for (var i = 0; i < products.length; i++) {
        document.getElementById("products").appendChild('<tr>' +
            '<td>' + products[i].date + '</td>' +
            '<td>' + products[i].name + '</td>' +
            '<td>' + products[i].money + '</td>' +
            '<td>' + products[i].currencyName + '</td>' +
            '</tr>');
    }
}


function getAllProducts (){
    $.ajax({
        url: mainUrl + "/product/getall",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            setProductsToContainer(data)
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setProductsToContainer(products) {
    document.getElementById("all-products").innerHTML = "";
    let containerOfProducts = $("#all-products");
    containerOfProducts.append('<th>Date</th>'+
    '<th>Name</th>'+
    '<th>Amount</th>'+
    '<th>Currency</th>');
    for (let product of products) {
        containerOfProducts.append('<tr>' +
            '<td>' + product.date + '</td>' +
            '<td>' + product.name + '</td>' +
            '<td>' + product.money + '</td>' +
            '<td>' + product.currencyName + '</td>' +
            '</tr>');
    }
}

function setActionOnGetAllButton() {
    $("#getall").click(function () {
    getAllProducts();
    })
}

function setActionOnDeleteButton() {
    $("#submit-delete").click(function () {
        let date = $("#delete-date").val();
        alert(date)
        $.ajax({
            url: mainUrl + "/product/delete?date=" + date,
            type: "DELETE",
            success: function () {
                alert("All products by the date deleted")
                getAllProducts();
            },
            error: function (error) {
                alert(error.message);
            }
        });
    })
}

function setActionOnReportButton() {
    $("#submit-report").click(function () {
        let year = $("#year").val();
        let currency = $("#currencyReportDrop").val();
        $.ajax({
            url: mainUrl + "/product/report?year=" + year,
            type: "GET",
            contentType: "application/json",
            success: function (data) {
                calculateReport(data, currency);
            },
            error: function (error) {
                alert(error.message);
            }
        });
    });

}

function calculateReport(listOfProducts, currency) {
    let sum = 0;
    for (var i = 0; i < listOfProducts.length; i++) {
        if (listOfProducts[i].currencyName === currency) {
            sum = sum + listOfProducts[i].money
        } else{
            var currencyFromList = listOfProducts[i].currencyName;
            var moneyFromList = listOfProducts[i].money;
           var c = converte (currencyFromList, currency, moneyFromList);
            sum = sum + c;
        }
            }
let reportTable = $("#report-table")
    reportTable.append('<tr>' +
        '<td>' + sum + '</td>' +
        '<td>' + currency + '</td>' +
        '</tr>');
}

function converte(currencyFromList, currency, moneyFromList) {
    $.ajax({
        url: "http://data.fixer.io/api/latest?access_key=" +api_key,
        dataType: 'jsonp',
        success: function (response) {
            alert (Object.values(response.rates.currencyFromList));
            return moneyFromList*Object.values(response.rates.currencyFromList);
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

