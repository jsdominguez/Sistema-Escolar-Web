$(function() {

    

    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Cantidad de Alumnos',
                data: [],
                fill: false,
                responsive: true,
                tension: 0.1,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                     min: 0,
	                ticks: {
			          // forces step size to be 50 units
			          stepSize: 1
			        }
                }
            }
        }
    });



    var ctx2 = document.getElementById('myChart2').getContext('2d');
    var myChart2 = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [{
                label: 'Cantidad de Docentes',
                data: [],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    ticks: {
			          // forces step size to be 50 units
			          stepSize: 1
			        }
                }
                 
            }
        }
    });




    var ctx3 = document.getElementById('myChart3').getContext('2d');
    var myChart3 = new Chart(ctx3, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [

            {
                label: 'Primaria',
                data: [],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            },

			{
                label: 'Secundaria',
                data: [],
                backgroundColor: [
                     'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                   'rgba(54, 162, 235, 1)',
                ],
                borderWidth: 1
            },            

            ],

        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    ticks: {
			          // forces step size to be 50 units
			          stepSize: 1
			        }
                }
            }
        }
    });

fnEnviarPeticion(new FormData(document.getElementById("reporte01")),1);
fnEnviarPeticion(new FormData(document.getElementById("reporte02")),2);
fnEnviarPeticion(new FormData(document.getElementById("reporte03")),3);

$("#btnSubmitFrmDate01").click(function(){
    var formData = new FormData(document.getElementById("reporte01"));
    fnEnviarPeticion(formData,1);
})

$("#btnSubmitFrmDate02").click(function(){
    var formData = new FormData(document.getElementById("reporte02"));
    fnEnviarPeticion(formData,2);
})


$("#btnSubmitFrmDate03").click(function(){
    var formData = new FormData(document.getElementById("reporte03"));
    fnEnviarPeticion(formData,3);
})


$("#btnDownloadReporteExcel01").click(function(){
    var datos = [];
    $("#reporte01 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=1&report=xlsx&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})


$("#btnDownloadReporteExcel02").click(function(){
    var datos = [];
    $("#reporte02 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=2&report=xlsx&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})



$("#btnDownloadReporteExcel03").click(function(){
    var datos = [];
    $("#reporte03 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=3&report=xlsx&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})


$("#btnDownloadReportePdf01").click(function(){
    var datos = [];
    $("#reporte01 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=1&report=pdf&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})

$("#btnDownloadReportePdf02").click(function(){
    var datos = [];
    $("#reporte02 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=2&report=pdf&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})

$("#btnDownloadReportePdf03").click(function(){
    var datos = [];
    $("#reporte03 > input").each(function(i){
        datos[i] = $(this).val();
    })

    window.location="/ProyectoIntegrador2/Srvlt_Reportes?chart=3&report=pdf&fechaMin="+datos[1]+"&fechaMax="+datos[2];
})

    function fnEnviarPeticion(formData,numChart){

        $.ajax({

                url: "/ProyectoIntegrador2/Srvlt_Reportes",
                type: "POST",
                data: formData,
                cache: false,
                contentType: false,
                processData: false
        }).done(function(response) {
           if(numChart == 1){
             myChart.data.datasets[0].data = response.datos;
             myChart.data.labels = response.info;
             myChart.update('active');
           }

           if(numChart == 2){
             myChart2.data.datasets[0].data = response.datos;
             myChart2.data.labels = response.info;
             myChart2.update('active');
           }

           if(numChart == 3){
             myChart3.data.datasets[0].data = response.datosx1;
             myChart3.data.datasets[1].data = response.datosx2;
             myChart3.data.labels = response.infox;
             myChart3.update('active');
           }
           
        });

    }

})