import { Pipe, PipeTransform } from '@angular/core';
import { FilesComponent } from '../files.component';

@Pipe({
  name: 'filter',
  standalone: true
})
export class FilterPipe implements PipeTransform {

  transform(files : any[], page: number = 0, search: string = ''): any[] {

    if(search.length === 0)
    return files.slice(page, page + 5);

    const filterContent = files.filter(file => file.content.toLowerCase().includes(search.toLocaleLowerCase()));

    return filterContent.slice(page, page + 5);
  }

}
