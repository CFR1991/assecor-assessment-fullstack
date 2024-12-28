import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MatSortModule, Sort } from '@angular/material/sort';
import { PersonDto } from '../../../../generated_sources';
import { NavigationService } from '../../shared/navigation/navigation.service';

@Component({
  selector: 'app-person-table',
  imports: [CommonModule, HttpClientModule, MatSortModule],
  templateUrl: './person-table.component.html',
  styleUrls: ['./person-table.component.scss']
})
export class PersonTableComponent implements OnChanges {

  @Input()
  persons!: PersonDto[];
  navigation = inject(NavigationService);
  sortColumn: string = 'id';
  sortDirection: boolean = true; // true for ascending, false for descending
  sort: Sort;


  public ngOnChanges(changes: SimpleChanges): void {
    if (changes['persons']) {
      this.sortData();
    }
  }

  protected setSortAndSortData(sort: Sort): void {
    this.sort = sort;
    this.sortData();
  }

  private sortData(): void {
    if (!this.sort || !this.sort.active || this.sort.direction === '') {
      this.persons;
      return;
    }

    this.persons = this.persons.sort((a: PersonDto, b: PersonDto) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'firstname': return this.compare(a.firstname, b.firstname, isAsc);
        case 'lastname': return this.compare(a.lastname, b.lastname, isAsc);
        case 'colour': return this.compare(a.colour, b.colour, isAsc);
        case 'zipcode': return this.compare(a.zipcode, b.zipcode, isAsc);
        case 'city': return this.compare(a.city, b.city, isAsc);
        default: return 0;
      }
    });
  }

  private compare(a: string, b: string, isAsc: boolean): number {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}