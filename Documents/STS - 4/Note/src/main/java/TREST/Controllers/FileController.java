package TREST.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import TREST.Services.FileStorageService;
import TREST.VO.UploadFileResponseVO;
import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController 
{

	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/upload")
	public UploadFileResponseVO uploadFile(@RequestParam("file")  MultipartFile file) throws Throwable
	{
		var FileName = fileStorageService.storeFile(file);
		String FileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/").path(FileName).toUriString();
		return  new UploadFileResponseVO(FileName, FileUri, file.getContentType(), file.getSize());
	}
	
	
	public UploadFileResponseVO uploadMultipleFiles(@RequestParam("filelist")   MultipartFile[] files)
	{
		return null;
	}
	
	@PostMapping("/multiupload")
	public List<UploadFileResponseVO> handleFileUploadMultiple(
		    @RequestParam("filelist") MultipartFile[] files) throws IOException 
	{	  
		return Arrays.asList(files)
				.stream()
				.map(file -> {
					try {
						return uploadFile(file);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(
		@PathVariable String filename, HttpServletRequest request) 
	{	
		Resource resource = (Resource) fileStorageService.loadFileAsResource(filename);
		String contentType = "";
		
		try {
			contentType = request.getServletContext().getMimeType(((org.springframework.core.io.Resource) resource).getFile().getAbsolutePath());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (contentType.isBlank()) contentType = "application/octet-stream";
		
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(
				HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + ((org.springframework.core.io.Resource) resource).getFilename() + "\"")
			.body(resource);
	}
}
