$(document).ready(function(){
		
			
			$('#button1').click( function() {
			    if( $(this).val() == '접기' ) {
			      $(this).val('더보기');
			      $('#pictureview').css({
						'height': '700px'
					})
					
					$('#profile').css({
						'height' : '1135px'
					})
					
					$( 'html, body' ).stop().animate( { scrollTop : '0' } )

					
			    }
			    
			    else {
			      $(this).val('접기');
			      $('#pictureview').css({
						'height': 'auto',
					   'overflow': 'hidden'
					})
					
					$('#profile').css({
						'height' : 'auto'
					})
					
			    }
			});
			
			
			
	});