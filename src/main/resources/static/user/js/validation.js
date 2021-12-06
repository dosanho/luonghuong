function validation(options){
    var selectorRules = {}
    //  funtion lấy ra parent của ô input
    function getParentInput(element , selector){
        while(element.parentElement){
            if(element.parentElement.matches(selector))

                return element.parentElement

            element = element.parentElement
        }
    }

    //  funtion validate
    function validate(inputElement , errorElement , rule){
        var spanElement = errorElement.querySelector(options.error)
        var rules = selectorRules[rule.selector];
        for(let i = 0; i< rules.length ;++i){

            switch(inputElement.type){
                case"radio":
                case"checkbox":
                    var errorMessage = rules[i](formElement.querySelector(rule.selector + ':checked'))
                    break;
                default:
                    var errorMessage = rules[i](inputElement.value)
            }

            if(errorMessage) break;
        }

        if(errorMessage){
            spanElement.innerText = errorMessage;
            errorElement.classList.add('invalid')
        }
        else{
            spanElement.innerText = '';
            errorElement.classList.remove('invalid')
        }
        return !errorMessage
    }
    // sử dụng DOM lấy dữ liệu chuyển vào validate()
    var formElement = document.querySelector(options.form);
    if(formElement){
        // sử lý sumbit

        formElement.onsubmit = (e) =>{
            e.preventDefault();
            var isFormValid = true;
            options.rules.forEach(rule =>{
                var inputElements = formElement.querySelectorAll(rule.selector)
                Array.from(inputElements).forEach(inputElement =>{
                    var errorElement =getParentInput(inputElement,options.formGroupSelector)
                    var isValid = validate(inputElement,errorElement,rule) // có value trả về true , ko thì trả về false
                    if(!isValid){
                        isFormValid = false;
                    }
                })
            })
            if(isFormValid){
                if(typeof options.onSubmit === 'function'){
                    var enableInput = formElement.querySelectorAll('[name]')
                    var formValues = Array.from(enableInput).reduce( function(values , input){
                        switch(input.type){
                            case "checkbox":
                                if(!input.checked){
                                    return values
                                }
                                if(!Array.isArray(values[input.name])){
                                    values[input.name] = []
                                }
                                values[input.name].push(input.value)
                                break;
                            case "radio":
                                if(input.checked){
                                    values[input.name] = input.value
                                    return values
                                }

                                break;
                            case "file":
                                values[input.name] = input.files
                                break
                            default:
                                values[input.name] = input.value
                        }

                        return values
                    },{})
                    options.onSubmit(formValues)
                } else{
                    // ko có onSumbit
                    formElement.submit()
                }
            }
        }

        // lặp rules
        options.rules.forEach(rule => {
            if(Array.isArray(selectorRules[rule.selector])){
                selectorRules[rule.selector].push(rule.test)
            } else{
                selectorRules[rule.selector] = [rule.test];
            }
            var inputElements = formElement.querySelectorAll(rule.selector)
            Array.from(inputElements).forEach(inputElement =>{
                var errorElement =getParentInput(inputElement,options.formGroupSelector)
                if(inputElement){

                    inputElement.onblur = ()=>{
                        validate(inputElement,errorElement,rule)
                    }
                    inputElement.oninput = () =>{
                        errorElement.querySelector(options.error).innerText = ''
                        errorElement.classList.remove('invalid')
                    }
                }
            });
        });
    }
}
// yêu cầu nhập trường này
validation.isRequired = function(selector , message){
    return {
        selector:selector,
        test:function(value){
            return value ? undefined: message || 'vui lòng nhập trường này'
        }
    }
}

validation.isEmail = (selector,message) => {
    return{
        selector:selector,
        test : (value)=>{
            regex =  /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
            return regex.test(value) ? undefined : message || "trường này phải là email"
        }
    }
}
validation.isNumber = (selector,message) => {
    return{
        selector:selector,
        test : (value)=>{
            if (value != ""){
                regex =  /^([1-9]\d*(\.)\d*|0?(\.)\d*[1-9]\d*|[1-9]\d*)$/
                return regex.test(value) ? undefined : message || "trường này phải là số"
            }
        }
    }
}
validation.isMinLength = function(selector, min , message){
    return {
        selector:selector,
        test:function(value){
            return value.trim().length >= min ? undefined: message || `độ dài phải lớn hơn hoặc bằng ${min} kí tự`
        }
    }
}

validation.isPassword_confirm = function(selector, getPassword , message){
    return {
        selector:selector,
        test:function(value){
            return value.trim() === getPassword() ? undefined: message || `vui lòng nhập lại trường này`
        }
    }
}

validation.isImage = (selector,message) => {
    return{
        selector:selector,
        test : (value)=>{
            regex =  /.*\.(jpg|png)$/igm
            return regex.test(value) ? undefined : message || "ảnh phải .jpg"
        }
    }
}


validation.isSellThanEntry = function(selector, getPriceSell , message){
    return {
        selector:selector,
        test:function(value){
           if (value != ""){
               return +value < +getPriceSell() ? undefined: message || `Giá nhập phải nhỏ hơn giá bán`
           }
        }
    }
}