import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(searchList : any , search : string): any {

    if (searchList && search)
      return searchList.filter(
        (d : any) =>
          d.toLowerCase()
        .indexOf(search.toLowerCase()) > -1 
      );


    return searchList;
  }

}
