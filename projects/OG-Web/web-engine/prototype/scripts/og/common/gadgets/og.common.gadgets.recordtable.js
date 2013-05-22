/**
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.common.gadgets.RecordTable',
    dependencies: ['og.common.gadgets.manager'],
    obj: function () {
        var RecordTable = function (config) {
            if (!config) og.dev.warn('og.common.gadgets.RecordTable: Missing param [config] to constructor.');

            var form, data = config.data, headers = data.headers, rows = data.rows,
                data_types = headers.map(function (header) {return {id: header.id, data_type: header.data_type};}),
                actions = {
                    cell: [ {name: 'edit'} ],
                    row: [
                        {name: 'edit'},
                        {name: 'save'},
                        {name: 'delete'},
                        {name: 'revert'}
                    ],
                    table: [ {name: 'add'} ]
                },
                field_types = rows.map(function (row) {
                    return {
                        row: row.id,
                        cols: row.cells.map(function (cell, idx) {
                            return {
                                id: cell.id,
                                data_type: data_types[idx].id === cell.id ? data_types[idx].data_type : void 0
                            };
                        })
                    };
                }),
                mock = {
                    'string': {val: 'String Cell'},
                    'number': {val: 'Number Cell'},
                    'array': {opts: ['Array Cell A', 'Array Cell B']}
                },
                selectors = {
                    table_rows: '.OG-gadget-recordtable tbody tr'
                };

            var add_handler = function () {
                var row_id = 'row-' + ($('.'+selectors.table_rows).length+1);
                new form.Block({
                    module: 'og.views.gadgets.recordtable.body_tash',
                    generator: function (handler, template, data) {
                        data.rows = [{
                            id: row_id,
                            cells: headers.map(function (entry) {
                                return {
                                    id: entry.id,
                                    text: (Handlebars.compile(create_field_type({
                                        type: entry.data_type,
                                        id: row_id + ' ' + entry.id
                                    })))(mock[entry.data_type])
                                };
                            }),
                            state: 'edit'
                        }];
                        data.actions = actions;
                        handler(template(data));
                    }
                }).html(function (html) {
                    $('.OG-gadget-recordtable tbody').append(html);
                });
            };

            var create_field_type = function (field) {
                switch (field.type) {
                    case 'string':
                    case 'number':
                        return '<input type="text" value="{{val}}" />';
                    case 'array':
                        return '<select>{{#each opts}}<option>{{this}}</option>{{/each}}</select>';
                    case 'boolean':
                        return '<input checked name="'+field.id+'" type="radio" value="true">true</input> '+
                               '<input name="'+field.id+'" type="radio" value="false">false</input>';
                }
            };

            var event_delegate = function (event) {
                var $elem = $(event.srcElement || event.target);
                if ($elem.data('action') === 'add') return add_handler();
            };

            var load_handler = function () {};

            form = new og.common.util.ui.Form({
                data: {},
                module: 'og.views.gadgets.recordtable.recordtable_tash',
                selector: '.' + config.container
            });
            form.children.push(
                new form.Block({
                    module: 'og.views.gadgets.recordtable.header_tash',
                    extras: {headers: data.headers}
                }),
                new form.Block({
                    module: 'og.views.gadgets.recordtable.footer_tash',
                    extras: {actions: actions, colspan: data.headers.length+1}
                }),
                new form.Block({
                    module: 'og.views.gadgets.recordtable.body_tash',
                    extras: {rows: data.rows, actions: actions}
                })
            );
            form.on('form:load', load_handler);
            form.on('click', '.og-cell-action', event_delegate);
            form.dom();
        };

        RecordTable.prototype = new og.common.util.ui.Block();
        return RecordTable;
    }
});