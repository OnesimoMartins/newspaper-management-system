import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/core/models/category.model';
import { SnackBarService } from 'src/app/core/services/snack-bar-service.service';
import { ArticleService } from '../../services/article.service';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-app-article-form',
  templateUrl: './app-article-form.component.html',
  styleUrls: ['./app-article-form.component.css'],
})
export class AppArticleFormComponent implements OnInit {

  disableSubmit: boolean = false;
  fileBytes: any;
  fileSelectText="seleccione um ficheiro..."
  form!: FormGroup;

  categories:Category[]=[]

  constructor(private categoryService:CategoryService,
              private articleService:ArticleService,
              private snackBarService:SnackBarService) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      tittle: new FormControl('', Validators.required),
      summary: new FormControl(''),
      category_id: new FormControl('', Validators.required),
      keywords: new FormControl('', Validators.required)
    });

    this.categoryService.getCategories().subscribe(it=>this.categories=it)
  }

  createArticle() {

    this.articleService.postArticle(this.form.value).subscribe(
      it=>{
    this.articleService.putArticleText(this.fileBytes,it.id).subscribe(
    ()=>this.snackBarService.openSnackBar("Artigo criado com sucesso")
    )
  })

  }

  onFileSelected() {

    let input:any=document.getElementById("file")!
    this.fileBytes = input.files[0];

      this.fileSelectText="Ficheiro seleccionado"

  }

  hasNotFile(){
    return this.fileSelectText=="seleccione um ficheiro..."
  }
}
