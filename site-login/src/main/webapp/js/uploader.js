$(function() {

	// Change this to the location of your server-side upload handler:
	$('#anchoruploadprogress').hide();
	$('#anchoruploadfile')
			.fileupload(
					{
						url : registerService.UPLOAD_FILE,
						dataType : 'json',
						autoUpload : false,
						type : 'POST',
						contenttype : false,
						replaceFileInput : false,
						done : function(e, data) {
							$('#progressText').html(
									"Cert uploaded successfully");
							$('#anchoruploadfiles').empty();
						},
						progressall : function(e, data) {
							var progressval = parseInt(data.loaded / data.total
									* 100, 10);
							//$('#progress').fadeIn();
							//$('#progress .progress-bar').css('width', progress + '%');
							if (progressval < 99) {
								$(
										'#anchoruploadwidget .blockMsg .progressorpanel .lbl')
										.text('Uploading...');
								$(
										'#anchoruploadwidget .blockMsg .progressorpanel .progressor')
										.text(floorFigure(data.loaded/ data.total* 100, 0).toString()
														+ "%");
							} else {
								$('.blockMsg .progressorpanel .lbl').text(
										'Updating Bundle...');
								$('.blockMsg .progressorpanel .progressor')
										.text('');
							}
						}
					})
			.on('fileuploadadd',function(e, data) {
						$('#anchoruploadsubmit').unbind("click");
						$('#anchoruploadfiles').empty();
						data.context = $('<div/>').appendTo(
								'#anchoruploadfiles');
						$.each(data.files, function(index, file) {

							var node = $('<p/>').append(
									$('<span/>').text(file.name));

							node.appendTo(data.context);
						});

						$('#anchoruploadform .formError').hide(0);
						data.context = $('#anchoruploadsubmit').click(
										function(e) {
											var jform = $('#anchoruploadform');
											//jform.validationEngine('hideAll');
											jform
													.validationEngine({
														promptPosition : "centerRight",
														validateNonVisibleFields : true,
														updatePromptsPosition : true
													});
											if (jform.validationEngine('validate')) {
												$('#anchoruploadform .formError').hide(0);
												data.submit();
											} else {
												//jform.validationEngine({validateNonVisibleFields: true, updatePromptsPosition:true});
												$('#anchoruploadform .formError').show(0);
												$('#anchoruploadform .anchoruploadfileformError').prependTo('#anchoruploaderrorlock');
											}
										});
					}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');

	$('#anchoruploadfile').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});

	$('#anchoruploadfile-btn').bind('click', function(e, data) {
		$('#anchoruploadform').trigger('reset');
		$('#anchoruploadsubmit').unbind("click");

		$('#anchoruploadfiles').empty();

		$('#anchoruploadform .formError').hide(0);
		$('#progressText').html("");

	});

});