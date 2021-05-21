 <!-- CSS INCLUDE -->  
<style>

	.modal-dialog {
		  display: flex;
		  flex-direction: column;
		  justify-content: center;
		  overflow-y: auto;
		  min-height: calc(100vh - 60px);
		  @media (max-width: 767px) {
		    min-height: calc(100vh - 20px);
		  }
		}


    #loader {
        border: 12px solid #f3f3f3;
        border-radius: 50%;
        border-top: 12px solid #444444;
        width: 70px;
        height: 70px;
        animation: spin 1s linear infinite;
    }
      
    @keyframes spin {
        100% {
            transform: rotate(360deg);
        }
    }
      
      
    .center {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: auto;
    }


    table.responsive tbody tr:hover {
  		background-color: #d4d4d4;
	}
 
	table.responsive tbody tr:hover > .sorting_1 {
 		 background-color: #d4d4d4;
	}

</style>

 		<link rel="stylesheet" type="text/css" id="theme" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/css/theme-default.css"/>
 		
 		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/datatables/DataTables-1.10.24/css/dataTables.bootstrap.min.css"/>

 		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/datatables/Responsive-2.2.7/css/responsive.bootstrap.min.css"/> 

 		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/waitme/waitMe.min.css"/> 

		<style type="text/css">
				@font-face {
			  font-family: 'MyWebFont';
			  src: url('${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap/fonts/glyphicons-halflings-regular.eot'); /* IE9 Compat Modes */
			  src: url('${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap/fonts/glyphicons-halflings-regular.woff2') format('woff2'), /* Super Modern Browsers */
			       url('${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap/fonts/glyphicons-halflings-regular.woff') format('woff'), /* Pretty Modern Browsers */
			       url('${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap/fonts/glyphicons-halflings-regular.ttf')  format('truetype'), /* Safari, Android, iOS */
			       url('${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap/fonts/glyphicons-halflings-regular.svg') format('svg'); /* Legacy iOS */
			}
-->
		</style>              