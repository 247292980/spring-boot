new Vue({
    el: '#app',
    data: {
        message: 'mvvc hi!',
        htmlMsg: '<h1>hi!</h1>',
        isClass1: false,
        counter: 0
    },
    methods: {
        count: function (event) {
            // `this` 在方法里指当前 Vue 实例
            alert('count 1!');
            this.counter += 1;
            // `event` 是原生 DOM 事件
            if (event) {
                alert(event.target.tagName);
            }
        }
    }
})