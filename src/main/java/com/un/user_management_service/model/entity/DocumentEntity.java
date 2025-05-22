package com.un.user_management_service.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.w3c.dom.DocumentType;

import com.un.user_management_service.constant.EntityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id", updatable = false, nullable = false)
    private Long documentId;

    @NotBlank
    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @NotBlank
    @Column(name = "stored_file_name", nullable = false, unique = true)
    private String storedFileName;

    @NotBlank
    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Positive
    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_hash")
    private String fileHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 30)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false, length = 30)
    @Pattern(regexp = "^(USER|CONTRACT)$", message = "Role must be 'USER' or 'CONTRACT'")
    private EntityType entityType;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Size(max = 500)
    @Column(name = "description")
    private String description;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "upload_date_time", nullable = false)
    private LocalDateTime uploadDateTime;

    @NotNull
    @Column(name = "uploaded_by_user_id", nullable = false)
    private Long uploadedByUserId;

    @CreatedDate
    @Column(name = "created_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}