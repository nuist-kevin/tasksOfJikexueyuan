/******************************************************************************************************
 ********   简易计算器
 ********   将用户操作分为4个阶段：0为初始阶段，1为已输入操作数1，2为已输入操作符，3为已输入操作数2, 4为已输入等号
 ********   根据所处阶段不同，不同类型的按钮点击后触发的事件结果也不一样
 ********   兼容了一些异常操作，如：未输入操作数2直接点击等号、连续计算等……
 ******************************************************************************************************/

//操作数初始值值为0，兼容直接点击操作符的计算行为
var operand1 = '0';
var operand2 = '';
var operator = '';
var result = '0';
var phase = 0;

//为所有按钮添加鼠标悬停效果
var buttons = document.querySelectorAll(".btn");
for (var index = 0; index < buttons.length; index++) {
    buttons[index].addEventListener('mouseover', function () {
        this.classList.add('buttonOn');
    });
    buttons[index].addEventListener('mouseout', function () {
        this.classList.remove('buttonOn');
    });

}

//数字按钮
var numBtns = document.querySelectorAll(".num");
for (var idx = 0; idx < numBtns.length; idx++) {
    numBtns[idx].onclick = function () {
        if (phase == 0 || phase == 1) {
            phase = 1;
            if (operand1 == '0') {
                operand1 = this.value;
            } else {
                operand1 += this.value;
            }
            result = operand1;
        }
        if (phase == 2 || phase == 3) {
            phase = 3;
            if (operand1 == '0') {
                operand2 = this.value;
            } else {
                operand2 += this.value;
            }
            result = operand2;
        }
        if (phase == 4) {
            phase = 1;
            result = '';
            operand2 = '';
            operand1 = this.value;
        }
        document.querySelector("#result").value = result;
        document.getElementById("typeRecord").value = parseFloat(operand1).toString() + ' ' + operator + ' ' + (operand2 ? parseFloat(operand2).toString() : '');

    }
}

//小数点按钮
document.querySelector(".dot").onclick = function () {
    switch (phase) {
        //兼容应该输入数字时直接点击小数点按钮，此时默认为小数点前加上0
        case 0:
            phase = 1;
            operand1 = '0.';
            result = operand1;
            break;
        case 1:
            if (!operand1.match("\\.")) {
                operand1 += '.';
                result = operand1;
            }
            break;
        case 2:
            phase = 3;
            operand2 = '0.';
            result = operand2
            break;
        case 3:
            if (!operand2.match("\\.")) {
                operand2 += '.';
                result = operand2;
            }
            break;
        case 4:
            phase = 1;
            operand2 = '';
            operand1 = '0.';
            result = operand1;

    }
    document.querySelector("#result").value = result;
    document.getElementById("typeRecord").value = parseFloat(operand1).toString() + ' ' + operator + ' ' + (operand2 ? parseFloat(operand2).toString() : '');
}

//操作符按钮
var operBtns = document.querySelectorAll(".operator");
for (var i = 0; i < operBtns.length; i++) {
    operBtns[i].onclick = function () {
        if (phase != 0) {
            if (phase == 3) {
                calculate();
                operand1 = result.toString();
                operand2 = '';
            }
            if (phase == 4) {
                operand1 = result.toString();
                operand2 = '';
            }
            phase = 2;
            operator = this.value;
            document.getElementById("typeRecord").value = parseFloat(operand1).toString() + ' ' + operator + ' ' + (operand2 ? parseFloat(operand2).toString() : '');
        }

    }
}

//退格按钮
document.querySelector(".del").onclick = function () {
    switch (phase) {
        case 0:

        case 1:
            if (operand1.length >= 2) {
            operand1 = operand1.slice(0, -1);
            } else {
                operand1 = '0';
            }
            result = operand1;
            break;
        case 3:
            if (operand2.length >= 2) {
                operand2 = operand2.slice(0, -1);
            } else {
                operand2 = '0';
            }
            result = operand2;
            break;
        case 4:
            phase = 1;
            result = '';
            operand2 = '';
            operand1 = '0.';
    }
    document.querySelector("#result").value = result;
    document.getElementById("typeRecord").value = parseFloat(operand1).toString() + ' ' + operator + ' ' + (operand2 ? parseFloat(operand2).toString() : '');
}

//清除按钮
document.querySelector(".clear").onclick = function () {
    operand1 = '';
    operand2 = '';
    operator = '';
    result = '';
    document.querySelector("#result").value = '0';
    document.getElementById("typeRecord").value = '0';
    phase = 0;
}

//等于按钮
document.querySelector(".equal").onclick = function () {
    if (phase != 0 && phase != 1) {
        if (phase == 2) {
            operand2 = operand1;
        } else if (phase == 4) {
            operand1 = result.toString();
        }
        calculate();
        phase = 4;
        document.getElementById("typeRecord").value = '';
    }
}

function calculate() {
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
        if (parseInt(operand2) == 0) {
            result = '除数不能为零'
        } else {
            result = floatDivide(operand1, operand2);
        }
    }
    if (operator == '%') {
        if (parseInt(operand2) == 0) {
            result = '不能模零'
        } else {
            result = floatMod(operand1, operand2);
        }
    }

    document.querySelector("#result").value = result;

}


/**** ********************************************************************************
 ***** 重写浮点数四则运算，解决不精确问题，如0.1 + 02 不等于0.3
 ***** 先取出两个操作数小数点后最长的位数，如4位，再将两个操作数乘以10的4次方，以此全部转化为整数
 ***** 对转化后的整数做四则运算，得出结果后再除以10的4次方。
 ***** ********************************************************************************/
function getMultiple(str1, str2) {
    var pointlength1 = str1.split(".")[1] ? str1.split(".")[1].length : 0;
    var pointlength2 = str2.split(".")[1] ? str2.split(".")[1].length : 0;
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
    return (multiple * parseFloat(operand1) * (multiple * parseFloat(operand2))) / (multiple * multiple);
}

function floatDivide(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return multiple * parseFloat(operand1) / (multiple * parseFloat(operand2));
}

function floatMod(operand1, operand2) {
    var multiple = getMultiple(operand1, operand2);
    return (multiple * parseFloat(operand1) % (multiple * parseFloat(operand2))) / multiple;
}
