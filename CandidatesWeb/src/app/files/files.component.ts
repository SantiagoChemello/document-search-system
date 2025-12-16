import { Component, OnDestroy, OnInit } from '@angular/core';
import {Apollo, gql} from 'apollo-angular';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ExpandtextComponent } from '../expandtext/expandtext.component';
import { FilterPipe } from './pipes/filter.pipe';

const getAllFiles = gql`
  query getAllFiles {
    getAllFiles {
      id
      filename
      type
      content
    }
  }
`;

@Component({
  selector: 'app-files',
  standalone: true,
  imports: [CommonModule, ExpandtextComponent, FilterPipe],
  templateUrl: './files.component.html',
  styleUrl: './files.component.css'
})
export class FilesComponent implements OnInit, OnDestroy {

  loading: boolean = true;
  files: any[] = [];
  public page: number =0;
  public search: string = '';
  private querySubscription: Subscription = new Subscription;

constructor(private readonly apollo: Apollo){}

  ngOnInit(): void {
    
    this.querySubscription = this.apollo.watchQuery<any>({
      query: getAllFiles,
    }).valueChanges.subscribe(({data, loading}) => {
  
      this.loading = loading;
      console.log('Data:', data); 
      this.files = data.getAllFiles;
      expanded: false;
    });
  }

 ngOnDestroy(): void {
    this.querySubscription.unsubscribe();
  }

  nextPage(){
    this.page+=5;
  }

  prevPage(){
    if(this.page > 0)
    this.page-=5;
  }

  SearchContent(search: string){
    this.page = 0;
    this.search = search;
  }
}
