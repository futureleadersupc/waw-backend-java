package ga.wawupc.springapi.employers.api;

import ga.wawupc.springapi.employers.domain.service.CompanyService;
import ga.wawupc.springapi.employers.mapping.CompanyMapper;
import ga.wawupc.springapi.employers.resource.CompanyRequest;
import ga.wawupc.springapi.employers.resource.CompanyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Companies")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/companies")
public class CompaniesController {
  private final CompanyService service;
  private final CompanyMapper mapper;

  public CompaniesController(CompanyService service, CompanyMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @Operation(summary = "Get Companies", description = "Get all companies")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of companies found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResource.class))
    })
  })
  @GetMapping
  public Page<CompanyResource> getAllCompanies(Pageable pageable) {
    return mapper.modelListPage(service.getAll(), pageable);
  }

  @Operation(summary = "Get Company By Id", description = "Get an specific company by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found company by ID", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResource.class))
    })
  })
  @GetMapping("{id}")
  public CompanyResource getCompanyById(@PathVariable Long id) {
    return mapper.toResource(service.getById(id));
  }

  @Operation(summary = "Create Companies", description = "Create new companies")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Company was created", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResource.class))
    }),
    @ApiResponse(responseCode = "400", description = "Company was invalid")
  })
  @PostMapping
  public CompanyResource createCompany(@RequestBody CompanyRequest company) {
    return mapper.toResource(service.create(mapper.toModel(company)));
  }

  @Operation(summary = "Update Company", description = "Update a specific company by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Company was updated", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyResource.class))
    }),
    @ApiResponse(responseCode = "400", description = "Company was invalid")
  })
  @PutMapping("{id}")
  public CompanyResource updateCompany(@PathVariable Long id, @RequestBody CompanyRequest company) {
    return mapper.toResource(service.update(id, mapper.toModel(company)));
  }

  @Operation(summary = "Delete Company", description = "Delete a specific company by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Company was deleted", content = {
      @Content(mediaType = "application/json")
    }),
    @ApiResponse(responseCode = "404", description = "Company was not found", content = {
      @Content(mediaType = "application/json")
    })
  })
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
    return service.delete(id);
  }
}
