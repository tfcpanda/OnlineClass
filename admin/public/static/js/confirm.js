Confirm = {
    show: function (message, callback) {
        Swal.fire({
            title: '确认?',
            text: message,
            icon: '警告',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#dd3333',
            confirmButtonText: '确认'
        }).then((result) => {
            if (result.value) {
                if (callback) {
                    callback()
                }

            }
        })

    }
}



