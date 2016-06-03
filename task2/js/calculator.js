var operand1 = '';
var operand2 = '';
var operator = '';
var result = '';
var endFlag = false;

var buttons = document.querySelectorAll("button")
for (var index = 0; index < buttons.length; index++) {
    buttons[index].addEventListener('mouseover', changeBtnBgcolor)
    buttons[index].addEventListener('mouseout', function() {
        this.style.backgroundColor = 'white';
    });
}

function changeBtnBgcolor() {
    this.style.backgroundColor = '#B4C4F5';
}

document.getElementById("clear").onclick = function() {
    operand1 = '';
    operand2 = '';
    result = '';
    document.querySelector("#result").value = 0;
}

document.querySelector(".equal").onclick = function() {
    if (operator == '-') {
        result = floatMinus(operand1, operand2);
    }
    if (operator == '+') {
        result = floatAdd(operand1, operand2);
    }
    if (operator == '*') {
        result = floatMultiple(operand1, operand2);
    }
    if (operator == '/') {
        if(parseInt(operand2) == 0) {
            result = '除数不能为零'
        }
        result = floatDivide(operand1, operand2);
    }
    if (operator == '%') {
               if(parseInt(operand2) == 0) {
            result = '不能模零'
        }
        result = floatMod(operand1, operand2);
    }

    document.querySelector("#result").value = result;
    endFlag = true;
}

function getMultiple(str1, str2) {
    var pointlength1 = operand1.split(".", 2)[1].length;
    var pointlength2 = operand1.split(".", 2)[1].length;
    var power = (pointlength1 >= pointlength2) ? pointlength1 : pointlength2;
    return Math.pow(10, power);
}

function floatAdd(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) + multiple * parseFloat(operand2)) / multiple;
}

function floatMinus(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) - multiple * parseFloat(operand2)) / multiple;
}

function floatMultiple(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) * multiple * parseFloat(operand2)) / multiple;
}

function floatDivide(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) / (multiple * parseFloat(operand2))) / multiple;
}

function floatMod(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) % (multiple * parseFloat(operand2))) / multiple;
}
